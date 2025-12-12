package br.senac.rj.backend.controller;

import java.util.List;

import br.senac.rj.backend.entity.Pedido;
import br.senac.rj.backend.service.PedidoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pedido")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoController {

    private final PedidoService pedidoService = new PedidoService();

    // POST /api/pedido/salvar
    @POST
    @Path("/salvar")
    public Response salvar(Pedido pedido) {
        try {
            Pedido salvo = pedidoService.salvar(pedido);
            return Response.status(Response.Status.CREATED)
                    .entity(salvo)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Não foi possível salvar o pedido.\"}")
                    .build();
        }
    }

    // GET /api/pedido/{id}
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Pedido pedido = pedidoService.buscarPorId(id);
        if (pedido == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"erro\":\"Pedido não encontrado.\"}")
                    .build();
        }
        return Response.ok(pedido).build();
    }

    // GET /api/pedido/listar
    @GET
    @Path("/listar")
    public Response listarTodos() {
        List<Pedido> pedidos = pedidoService.listarTodos();
        return Response.ok(pedidos).build();
    }

    // GET /api/pedido/usuario/{usuarioId}
    @GET
    @Path("/usuario/{usuarioId}")
    public Response listarPorUsuario(@PathParam("usuarioId") Long usuarioId) {
        List<Pedido> pedidos = pedidoService.listarPorUsuario(usuarioId);
        return Response.ok(pedidos).build();
    }

    // PUT /api/pedido/{id}/status?valor=EM_ENTREGA
    @PUT
    @Path("/{id}/status")
    public Response atualizarStatus(
            @PathParam("id") Long id,
            @QueryParam("valor") String novoStatus) {

        if (novoStatus == null || novoStatus.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Informe o parâmetro 'valor' com o novo status.\"}")
                    .build();
        }

        try {
            pedidoService.atualizarStatus(id, novoStatus);
            return Response.ok("{\"mensagem\":\"Status atualizado com sucesso.\"}").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Não foi possível atualizar o status do pedido.\"}")
                    .build();
        }
    }

    // DELETE /api/pedido/{id}
    @DELETE
    @Path("/{id}")
    public Response cancelar(@PathParam("id") Long id) {
        try {
            pedidoService.cancelar(id);
            return Response.ok("{\"mensagem\":\"Pedido cancelado com sucesso.\"}").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Não foi possível cancelar o pedido.\"}")
                    .build();
        }
    }
}
