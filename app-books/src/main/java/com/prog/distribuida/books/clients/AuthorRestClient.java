package com.prog.distribuida.books.clients;

import com.prog.distribuida.books.dto.AuthorDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/authors")
//@RegisterRestClient(configKey = "AuthorRestClient")
@RegisterRestClient(baseUri = "stork://authors-api")
public interface AuthorRestClient {
    @GET
    @Path("/find/{isbn}")
    public List<AuthorDTO> findByBook(@PathParam("isbn") String isbn);
}
