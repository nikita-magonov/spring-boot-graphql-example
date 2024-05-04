package ru.hse.spring.boot.graphql.example.controllers;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import ru.hse.spring.boot.graphql.example.data.Author;
import ru.hse.spring.boot.graphql.example.data.AuthorRepository;
import ru.hse.spring.boot.graphql.example.data.Book;
import ru.hse.spring.boot.graphql.example.data.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @QueryMapping
    Book bookByIsbn(@Argument String isbn) {
        return bookRepository.getByIsbn(isbn);
    }

    @SchemaMapping
    List<Author> authors(Book book) {
        return book.getAuthorIds().stream()
                .map(authorRepository::getById)
                .collect(Collectors.toList());
    }
}
