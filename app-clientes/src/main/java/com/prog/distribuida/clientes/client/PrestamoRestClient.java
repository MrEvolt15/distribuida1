package com.prog.distribuida.clientes.client;

import com.prog.distribuida.clientes.dto.PrestamoDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/prestamos")
@RegisterRestClient(baseUri = "stork://prestamos-api")
public interface PrestamoRestClient {
    @GET
    @Path("/cliente/{clienteId}")
    public List<PrestamoDTO> prestamosPorCliente(@PathParam("clienteId") Integer clienteId);
}
