package com.prog.distribuida.clientes.repo;

import com.prog.distribuida.clientes.db.Clientes;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.client.Client;

import java.util.Optional;

@ApplicationScoped
@Transactional
public class ClientesRepo implements PanacheRepositoryBase<Clientes,Integer> {
    public Optional<Clientes> findByCedula(String cedula) {
        return this.find(
                "select c from Cliente c where c.cedula = ?1",
                cedula
        ).firstResultOptional();
    }
}
