package br.senac.rj.backend.filter;

import br.senac.rj.backend.service.AuthService;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

/**
 * Mecanismo que protege as rotas (endpoints) da sua API,
 * garantindo que apenas requisições com um JWT válido possam acessar os recursos.
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    private final AuthService authService = new AuthService();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();
        String method = requestContext.getMethod();

        // 1) Libera preflight de CORS (OPTIONS) para o Angular
        if ("OPTIONS".equalsIgnoreCase(method)) {
            return;
        }

        // 2) Rotas públicas (sem token)
        if (path.equals("health")
                || path.equals("usuario/login")
                || path.equals("usuario/salvar")
                // Fale Conosco (contato) público
                || path.equals("contato/salvar")) {
            return;
        }

        // 3) Pega o header Authorization
        String authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            abort(requestContext, "Token ausente ou formato inválido.");
            return;
        }

        // Extrai o token corretamente
        String token = authHeader.substring("Bearer ".length()).trim();

        // 4) Valida o token com AuthService
        if (!authService.validarToken(token)) {
            abort(requestContext, "Token inválido ou expirado.");
            return;
        }

        // Opcional: Recupera o e-mail do usuário do token
        String email = authService.getEmailDoToken(token);
        requestContext.setProperty("usuarioEmail", email);
    }

    private void abort(ContainerRequestContext ctx, String mensagem) {
        ctx.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"erro\":\"" + mensagem + "\"}")
                        .build()
        );
    }
}
