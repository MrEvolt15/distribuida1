package com.prog.distribuida.customers.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="purchase_orders")
@Getter @Setter
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "delivered_on")
    private LocalDate delivered_on;
    @Column(name = "placed_on")
    private LocalDate placed_on;

    private BigDecimal total;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;

}
