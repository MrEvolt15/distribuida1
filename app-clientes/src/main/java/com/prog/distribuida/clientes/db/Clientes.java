package com.prog.distribuida.clientes.db;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "clients")
@Getter
@Setter
@ToString
public class Clientes {
    @Id
    private Integer id;
    private String cedula;
    private String email;

}
