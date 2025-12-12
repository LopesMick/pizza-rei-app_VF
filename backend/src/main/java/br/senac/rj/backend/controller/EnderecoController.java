package br.senac.rj.backend.controller;

import br.senac.rj.backend.entity.Endereco;
import br.senac.rj.backend.service.EnderecoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 *
 * Controller responsável pelas rotas de Endereço.
 * Rotas protegidas por token (AuthFilter), exceto as liberadas no filtro.
 *
 * Base path: /api/endereco
 */
@Path("endereco")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EnderecoController {

    private final EnderecoService service = new EnderecoService();

    /**
     * POST /api/endereco/salvar
     * Body exemplo:
     * {
     *   "usuarioId": 1,
     *   "apelido": "Casa",
     *   "cep": "20000-000",
     *   "logradouro": "Rua X",
     *   "numero": "100",
     *   "complemento": "Apto 201",
     *   "bairro": "Centro",
     *   "cidade": "Rio de Janeiro",
     *   "estado": "RJ",
     *   "referencia": "Próximo ao mercado",
     *   "principal": true
     * }
     */
    @POST
    @Path("salvar")
    public Response salvar(Endereco endereco) {
        try {
            Endereco salvo = service.salvar(endereco);
            if (salvo == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"erro\":\"Não foi possível salvar o endereço.\"}")
                        .build();
            }
            return Response.ok(salvo).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Não foi possível salvar o endereço.\"}")
                    .build();
        }
    }

    /**
     * GET /api/endereco/listar
     */
    @GET
    @Path("listar")
    public Response listar() {
        try {
            List<Endereco> lista = service.listar();
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Não foi possível listar os endereços.\"}")
                    .build();
        }
    }

    /**
     * GET /api/endereco/listar-por-usuario/1
     */
    @GET
    @Path("listar-por-usuario/{usuarioId}")
    public Response listarPorUsuario(@PathParam("usuarioId") Long usuarioId) {
        try {
            List<Endereco> lista = service.listarPorUsuario(usuarioId);
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Não foi possível listar os endereços do usuário.\"}")
                    .build();
        }
    }

    /**
     * GET /api/endereco/buscar/1
     */
    @GET
    @Path("buscar/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            Endereco e = service.buscarPorId(id);
            if (e == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"erro\":\"Endereço não encontrado.\"}")
                        .build();
            }
            return Response.ok(e).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Não foi possível buscar o endereço.\"}")
                    .build();
        }
    }

    /**
     * DELETE /api/endereco/remover/1
     */
    @DELETE
    @Path("remover/{id}")
    public Response remover(@PathParam("id") Long id) {
        try {
            boolean ok = service.remover(id);
            if (!ok) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"erro\":\"Não foi possível remover o endereço.\"}")
                        .build();
            }
            return Response.ok("{\"mensagem\":\"Endereço removido com sucesso.\"}").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Não foi possível remover o endereço.\"}")
                    .build();
        }
    }
}

