package com.prog.distribuida.customers.dto;

import com.prog.distribuida.customers.db.PurchaseOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CustomersDTO {
    private Integer id;
    private String name;
    private String email;
    private List<PurchaseOrderDTO> purchaseOrders;
}
