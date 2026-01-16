package com.prog.distribuida.books.clients;

import com.prog.distribuida.books.dto.AuthorDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/customers")
//@RegisterRestClient(configKey = "AuthorRestClient")
@RegisterRestClient(baseUri = "stork://customers-api")
public interface CustomersRestClient {
    @GET
    List<Object> findAll();
}
