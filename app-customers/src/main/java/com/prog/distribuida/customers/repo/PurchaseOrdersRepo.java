package com.prog.distribuida.customers.repo;

import com.prog.distribuida.customers.db.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PurchaseOrdersRepo extends JpaRepository<PurchaseOrder, Integer> {
}
