package br.senac.rj.backend.config;

import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class JaxRsApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(br.senac.rj.backend.controller.AlunoController.class);
        resources.add(br.senac.rj.backend.controller.TurmaController.class);
        resources.add(br.senac.rj.backend.controller.UsuarioController.class);
        resources.add(br.senac.rj.backend.filter.AuthFilter.class);
        resources.add(br.senac.rj.backend.controller.HealthController.class);


        // Se o seu CorsFilter for um filtro JAX-RS (ContainerResponseFilter),
        // você pode registrar aqui também:
        // resources.add(br.senac.rj.backend.config.CorsFilter.class);

        return resources;
    }
}
