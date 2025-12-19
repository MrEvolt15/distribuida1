package com.prog.distribuida.prestamos.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClienteDTO {
    private Integer id;
    private String cedula;
    private String email;

}
