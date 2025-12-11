package br.senac.rj.backend.controller;

import br.senac.rj.backend.entity.Avaliacao;
import br.senac.rj.backend.service.AvaliacaoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 *
 * Controller responsável pela funcionalidade de avaliação do sistema.
 * Rotas protegidas por token (AuthFilter).
 *
 * Base path: /api/avaliacao
 */
@Path("avaliacao")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AvaliacaoController {

    private final AvaliacaoService service = new AvaliacaoService();

    /**
     * POST /api/avaliacao/salvar
     * Body: { "usuarioId": 1, "nota": 5, "comentario": "Muito bom!" }
     */
    @POST
    @Path("salvar")
    public Response salvar(Avaliacao avaliacao) {
        try {
            Avaliacao salvo = service.salvar(avaliacao);
            if (salvo == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"erro\":\"Não foi possível salvar a avaliação.\"}")
                        .build();
            }
            return Response.ok(salvo).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Não foi possível salvar a avaliação.\"}")
                    .build();
        }
    }

    /**
     * GET /api/avaliacao/listar
     */
    @GET
    @Path("listar")
    public Response listar() {
        try {
            List<Avaliacao> lista = service.listar();
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Não foi possível listar as avaliações.\"}")
                    .build();
        }
    }

    /**
     * GET /api/avaliacao/buscar/1
     */
    @GET
    @Path("buscar/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            Avaliacao a = service.buscarPorId(id);
            if (a == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"erro\":\"Avaliação não encontrada.\"}")
                        .build();
            }
            return Response.ok(a).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Não foi possível buscar a avaliação.\"}")
                    .build();
        }
    }

    /**
     * DELETE /api/avaliacao/remover/1
     */
    @DELETE
    @Path("remover/{id}")
    public Response remover(@PathParam("id") Long id) {
        try {
            boolean ok = service.remover(id);
            if (!ok) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"erro\":\"Não foi possível remover a avaliação.\"}")
                        .build();
            }
            return Response.ok("{\"mensagem\":\"Avaliação removida com sucesso.\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Não foi possível remover a avaliação.\"}")
                    .build();
        }
    }
}

