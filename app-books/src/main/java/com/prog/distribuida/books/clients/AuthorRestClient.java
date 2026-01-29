package com.prog.distribuida.books.clients;

import com.prog.distribuida.books.dto.AuthorDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
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
    @Retry(maxRetries = 2,delay = 100)
    @Fallback(fallbackMethod = "findByBooksFallback")
    public List<AuthorDTO> findByBook(@PathParam("isbn") String isbn);

    default List<AuthorDTO> findByBooksFallback(String isbn) {
        var dto = new AuthorDTO();
        dto.setId(0);
        dto.setName("notfound");

        return List.of(dto);
    }
}
