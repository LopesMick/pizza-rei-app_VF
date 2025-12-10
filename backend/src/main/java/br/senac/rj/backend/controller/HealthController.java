package br.senac.rj.backend.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("health")
public class HealthController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String health() {
        return "{\"status\":\"UP\"}";
    }
}
