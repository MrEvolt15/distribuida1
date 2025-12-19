package com.prog.distribuida.prestamos.client;

import com.prog.distribuida.prestamos.dto.ClienteDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/clientes")
@RegisterRestClient(baseUri = "stork://clientes-api")
public interface ClientesRestClient {
    @GET
    @Path("/{id}")
    public ClienteDTO findById(@PathParam("id") Integer id);
}
