package ru.hse.spring.boot.graphql.example.data;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class SimpleAuthorRepository implements AuthorRepository {

    private final List<Author> authors = Arrays.asList(
            new Author(1, "Илья", "Ильф"),
            new Author(2, "Евгений", "Петров"),
            new Author(3, "Антон", "Чехов")
    );

    @Override
    public Author getById(int id) {
        return authors.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
