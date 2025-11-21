package com.programacion.distribuida;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import java.time.LocalDateTime;

@Path("/hola")
public class HolaRest {

    @GET
    @Path("/{name}")
    @Produces("text/plain")
    public String hola (@PathParam("name")String name){
        //http://127.0.0.1:80.80/ctx
        return "hola"+name+" "+ LocalDateTime.now();
    }
}
