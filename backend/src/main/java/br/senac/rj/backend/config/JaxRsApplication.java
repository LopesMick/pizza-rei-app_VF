package br.senac.rj.backend.config;

import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import br.senac.rj.backend.controller.AlunoController;
import br.senac.rj.backend.controller.TurmaController;
import br.senac.rj.backend.controller.UsuarioController;
import br.senac.rj.backend.controller.HealthController;

// Quando você criar:
import br.senac.rj.backend.controller.CategoriaController;
import br.senac.rj.backend.controller.ProdutoController;
import br.senac.rj.backend.controller.ContatoController;
import br.senac.rj.backend.controller.EnderecoController;
import br.senac.rj.backend.controller.PedidoController;
import br.senac.rj.backend.controller.AvaliacaoController;

import br.senac.rj.backend.filter.AuthFilter;

@ApplicationPath("/api")
public class JaxRsApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();

        // Controllers existentes
        resources.add(AlunoController.class);
        resources.add(TurmaController.class);
        resources.add(UsuarioController.class);
        resources.add(HealthController.class);

        // Novos controllers da Pizzaria
        resources.add(CategoriaController.class);
        resources.add(ProdutoController.class);
        resources.add(ContatoController.class);
        resources.add(EnderecoController.class);
        resources.add(PedidoController.class);
        resources.add(AvaliacaoController.class);

        // Filtro de autenticação
        resources.add(AuthFilter.class);

        return resources;
    }
}
