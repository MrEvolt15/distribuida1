package com.prog.distribuida.books.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString
public class AuthorDTO {

    private Integer id;
    private String name;
    private Integer version;
}
