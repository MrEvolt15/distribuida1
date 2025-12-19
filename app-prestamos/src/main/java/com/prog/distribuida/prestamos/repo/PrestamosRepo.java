package com.prog.distribuida.prestamos.repo;

import com.prog.distribuida.prestamos.db.Prestamos;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;


@ApplicationScoped
@Transactional
public class PrestamosRepo implements PanacheRepositoryBase<Prestamos,Integer> {
    public List<Prestamos> findByClienteId(Integer clienteId) {
        return this.list(
                "select p from Prestamos p where p.clienteId = ?1",
                clienteId
        );
    }
}
