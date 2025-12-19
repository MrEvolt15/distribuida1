package com.prog.distribuida.clientes.rest;

import com.prog.distribuida.clientes.client.PrestamoRestClient;
import com.prog.distribuida.clientes.db.Clientes;
import com.prog.distribuida.clientes.dto.ClientePrestamosDTO;
import com.prog.distribuida.clientes.dto.PrestamoDTO;
import com.prog.distribuida.clientes.dto.PrestamoDetalleDTO;
import com.prog.distribuida.clientes.repo.ClientesRepo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigDecimal;
import java.util.List;

@Path("/clients")
@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ClientsRest {
    @Inject
    ClientesRepo clientesRepo;
    @Inject
    @ConfigProperty(name = "quarkus.http.port")
    Integer port;

    @Inject
    @RestClient
    PrestamoRestClient prestamoClient;

    @GET
    @Path("/{cedula}/prestamos")
    public Response prestamosPorCedula(@PathParam("cedula") String cedula) {

        Clientes cliente = clientesRepo.findByCedula(cedula)
                .orElseThrow(() -> new NotFoundException("Cliente no existe"));

        List<PrestamoDTO> prestamos =
                prestamoClient.prestamosPorCliente(cliente.getId());

        BigDecimal total = BigDecimal.valueOf(prestamos.stream()
                .mapToDouble(p -> p.montoTotal)
                .sum());

        ClientePrestamosDTO response = new ClientePrestamosDTO();
        response.cedula = cedula;
        response.montoTotalPrestamos = total;
        response.prestamos = prestamos;

        return Response.ok(response).build();
    }

}
