package ru.hse.spring.boot.graphql.example.data;

public interface AuthorRepository {

    Author getById(int id);
    void addAuthor(Author author);
    void deleteAuthor(int id);
}
