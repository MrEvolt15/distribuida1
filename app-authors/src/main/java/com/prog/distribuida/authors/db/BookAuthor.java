package com.prog.distribuida.authors.db;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.print.Book;

@Entity
@Table(name = "books_authors")
@Getter
@Setter
public class BookAuthor {
    @EmbeddedId
    private BookAuthorId id;

    @JoinColumn(name = "authors_id",nullable = false)
    @ManyToOne
    @MapsId("authorId")
    private Author author;
}
