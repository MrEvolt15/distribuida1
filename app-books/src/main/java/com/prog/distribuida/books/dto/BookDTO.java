package com.prog.distribuida.books.dto;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter@Setter
@ToString
public class BookDTO {

    private String title;
    private String isbn;
    private BigDecimal price;

    private Integer inventorySold;
    private Integer inventorySupplied;

    private List<AuthorDTO> authors;

    public BookDTO() {}
}
