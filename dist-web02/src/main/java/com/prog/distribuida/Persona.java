package com.prog.distribuida;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Persona {
    private String name;
    private LocalDateTime hechaAhora;
}
