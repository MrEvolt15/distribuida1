package com.prog.distribuida.prestamos.db;

import com.prog.distribuida.prestamos.dto.ClienteDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "prestamos")
@Getter
@Setter
@ToString
public class Prestamos {
    @Id
    private Integer id;

    private BigDecimal monto_total;
    private BigDecimal tasa_interes;

    private Integer clienteId;

}
