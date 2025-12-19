package com.prog.distribuida.clientes.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class PrestamoDTO {
    public Integer id;
    public double montoTotal;
    public BigDecimal tasaInteres;
}
