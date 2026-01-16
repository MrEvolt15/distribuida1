package com.prog.distribuida.customers.repo;

import com.prog.distribuida.customers.db.Customers;

import com.prog.distribuida.customers.dto.CustomersDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CustomersRepo extends JpaRepository<Customers, Integer> {
}
