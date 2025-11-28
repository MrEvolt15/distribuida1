package com.prog.distribuida.books.rest;

import com.prog.distribuida.books.clients.AuthorRestClient;
import com.prog.distribuida.books.dto.BookDTO;
import com.prog.distribuida.books.repo.BookRepository;
import com.prog.distribuida.books.servicios.MapperService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Map;

@Path("/books")
@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class BookRest {
    @Inject
    BookRepository bookRepository;

    @Inject
    ModelMapper mapper;

    @Inject
    @RestClient
    AuthorRestClient client;

//    @PostConstruct
//    void init(){
//        var authorsServer = "http://localhost:8070";
//        client = RestClientBuilder.newBuilder()
//                .baseUri(authorsServer)
//                .build(AuthorRestClient.class);
//    }

    @GET
    @Path("/{isbn}")
    public Response findByIsbn(@PathParam("isbn") String isbn) {



        return bookRepository.findByIdOptional(isbn)
                .map(book ->{
                    System.out.println("Buscando authors para el libro isbn= "+isbn);
                    var authors = client.findByBook(isbn);
                    var dto = new BookDTO();
                    mapper.map(book, dto);
                    dto.setAuthors(authors);
                    return Response.ok(dto).build();
                })
                .orElse(Response.status(Response.Status.NOT_FOUND).build());

//        var obj = bookRepository.findByIdOptional(isbn);
//
//        if(obj.isEmpty()) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//
//        BookDTO ret = new BookDTO();

//        mapper.map(obj.get(),ret);
//        ret.setIsbn(obj.get().getIsbn());
//        ret.setTitle(obj.get().getTitle());
//        ret.setInventorySold(obj.get().getInventory().getSold());
//        ret.setPrice(obj.get().getPrice());
//        ret.setInventorySupplied(obj.get().getInventory().getSupplied());
//
//        ret.setAuthors(
//                List.of()
//        );
//        return Response.ok(ret)
//                .build();
    }

    @GET
    public List<BookDTO> findAll() {
        return bookRepository.streamAll()
                .map(book -> {
                    var dto = new BookDTO();
                    mapper.map(book,dto);
                    return dto;
                })
                .map(book -> {
                    var authors = client.findByBook(book.getIsbn());
                    book.setAuthors(authors);
                    return book;
                })
                .toList();
    }
}
