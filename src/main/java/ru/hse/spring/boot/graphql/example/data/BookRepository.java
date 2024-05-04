package ru.hse.spring.boot.graphql.example.data;

public interface BookRepository {

    Book getByIsbn(String isbn);
}
