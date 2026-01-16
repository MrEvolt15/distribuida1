package com.prog.distribuida.customers.rest;

import com.prog.distribuida.customers.db.Customers;
import com.prog.distribuida.customers.db.PurchaseOrder;
import com.prog.distribuida.customers.dto.CustomersDTO;
import com.prog.distribuida.customers.dto.PurchaseOrderDTO;
import com.prog.distribuida.customers.repo.CustomersRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(value = "/customers",produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional
@AllArgsConstructor //crea constructor por defecto
public class CustomerRest {
    //@Autowired
    //CustomersRepository customersRepository;

    final CustomersRepo customersRepo;

    @GetMapping
    public List<CustomersDTO> findAll() {
        return customersRepo.findAll()
                .stream()
                .map(it -> {
                    var purchaseOrders = it.getOrders().stream()
                            .map(it2 -> {
                                return PurchaseOrderDTO.builder()
                                        .id(it2.getId())
                                        .placed_on(it2.getPlaced_on())
                                        .delivered_on(it2.getDelivered_on())
                                        .total(it2.getTotal())
                                        .status(it2.getStatus())
                                        .build();
                            })
                            .toList();
                    return CustomersDTO.builder()
                            .id(it.getId())
                            .name(it.getName())
                            .email(it.getEmail())
                            .purchaseOrders(purchaseOrders)
                            .build();
                })
                .toList();
    }
}
