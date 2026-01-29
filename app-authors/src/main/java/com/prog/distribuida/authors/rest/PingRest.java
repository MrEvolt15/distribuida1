package com.prog.distribuida.authors.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.health.Liveness;


@Path("/ping")
public class PingRest {

    @GET
    public String ping(){
        return "Pong";
    }
}
