package br.senac.rj.backend.controller;

import br.senac.rj.backend.entity.Contato;
import br.senac.rj.backend.service.ContatoService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/contato")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContatoController {

    private final ContatoService service = new ContatoService();

    // POST /api/contato/salvar
    @POST
    @Path("/salvar")
    public Response salvar(Contato contato) {
        return service.salvar(contato);
    }

    // GET /api/contato  (listar todos os contatos)
    @GET
    public Response listarTodos() {
        return service.listarTodos();
    }

    // GET /api/contato/usuario/{idUsuario}  (contatos de um usuário)
    @GET
    @Path("/usuario/{idUsuario}")
    public Response listarPorUsuario(@PathParam("idUsuario") Long idUsuario) {
        return service.listarPorUsuario(idUsuario);
    }
}

