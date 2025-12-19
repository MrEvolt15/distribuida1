package com.prog.distribuida.prestamos.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pagos")
@Getter
@Setter
@ToString
public class Pagos {
    @Id
    private Integer id;

    private BigDecimal monto_pago;
    private Date fecha_pago;

    @OneToMany
    @JoinColumn(name = "prestamo_id")
    private List<Prestamos> prestamos;
}
