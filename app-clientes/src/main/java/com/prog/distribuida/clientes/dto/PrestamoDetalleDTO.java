package com.prog.distribuida.clientes.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
public class PrestamoDetalleDTO {
    public Integer prestamoId;
    public BigDecimal montoTotal;
    public BigDecimal tasaInteres;
}