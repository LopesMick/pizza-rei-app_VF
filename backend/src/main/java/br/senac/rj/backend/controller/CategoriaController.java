package br.senac.rj.backend.controller;

import br.senac.rj.backend.entity.Categoria;
import br.senac.rj.backend.service.CategoriaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/categoria")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoriaController {

    private final CategoriaService service = new CategoriaService();

    @GET
    @Path("/listar")
    public List<Categoria> listar() {
        return service.listar();
    }

    @POST
    @Path("/salvar")
    public Categoria salvar(Categoria categoria) {
        return service.salvar(categoria);
    }

    @DELETE
    @Path("/remover/{id}")
    public String remover(@PathParam("id") Long id) {
        boolean ok = service.remover(id);
        return ok ? "{\"status\":\"OK\"}" : "{\"erro\":\"Categoria não encontrada.\"}";
    }
}
