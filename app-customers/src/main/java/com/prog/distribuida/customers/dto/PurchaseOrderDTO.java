package com.prog.distribuida.customers.dto;

import com.prog.distribuida.customers.db.Customers;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter@Setter
@Builder
public class PurchaseOrderDTO {
    private Integer id;

    private LocalDate delivered_on;

    private LocalDate placed_on;

    private BigDecimal total;
    private Integer status;

}
