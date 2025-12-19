package com.prog.distribuida.prestamos.rest;

import com.prog.distribuida.prestamos.db.Prestamos;
import com.prog.distribuida.prestamos.client.ClientesRestClient;
import com.prog.distribuida.prestamos.repo.PrestamosRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("/prestamos")
@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PrestamosRest {
    @Inject
    private PrestamosRepo prestamosRepo;
    @Inject
    @RestClient
    ClientesRestClient clienteClient;

    @GET
    @Path("/{id}")
    public Prestamos findById(@PathParam("id")Integer id) {
        return prestamosRepo.findById(Integer.valueOf(id));
    }
    @GET
    @Path("/cliente/{clienteId}")
    public List<Prestamos> prestamosPorCliente(@PathParam("clienteId") Integer clienteId) {

        // 1️⃣ Validar cliente remoto
        try {
            clienteClient.findById(clienteId);
        } catch (Exception e) {
            return null;
        }

        // 2️⃣ Obtener préstamos locales
        return prestamosRepo.findByClienteId(clienteId);
        //return Response.ok(prestamos).build();
    }
}
