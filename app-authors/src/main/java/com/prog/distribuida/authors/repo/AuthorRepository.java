package com.prog.distribuida.authors.repo;

import com.prog.distribuida.authors.db.Author;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class AuthorRepository implements PanacheRepository<Author> {


    public List<Author> findbyBook(String isbn){
        return this.list(
                "select o.author from BookAuthor o where o.id.bookIsbn = ?1",isbn

        );
    }
}
