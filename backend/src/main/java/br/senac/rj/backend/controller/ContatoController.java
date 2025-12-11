package br.senac.rj.backend.controller;

import br.senac.rj.backend.entity.Contato;
import br.senac.rj.backend.service.ContatoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/contato")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContatoController {

    private final ContatoService service = new ContatoService();

    @POST
    @Path("/salvar")
    public Contato salvar(Contato contato) {
        return service.salvar(contato);
    }
}
