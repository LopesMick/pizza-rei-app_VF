package br.senac.rj.backend.controller;

import br.senac.rj.backend.entity.Produto;
import br.senac.rj.backend.service.ProdutoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/produto")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoController {

    private final ProdutoService service = new ProdutoService();

    @GET
    @Path("/listar")
    public List<Produto> listar() {
        return service.listar();
    }

    @POST
    @Path("/salvar")
    public Produto salvar(Produto produto) {
        return service.salvar(produto);
    }

    @DELETE
    @Path("/remover/{id}")
    public String remover(@PathParam("id") Long id) {
        boolean ok = service.remover(id);
        return ok ? "{\"status\":\"OK\"}" : "{\"erro\":\"Produto não encontrado.\"}";
    }
}
