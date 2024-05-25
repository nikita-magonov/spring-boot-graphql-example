package ru.hse.spring.boot.graphql.example.data;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SimpleAuthorRepository implements AuthorRepository {

    private List<Author> authors = new ArrayList<>(List.of(
            new Author(1, "Илья", "Ильф"),
            new Author(2, "Евгений", "Петров"),
            new Author(3, "Антон", "Чехов")
    ));

    @Override
    public Author getById(int id) {
        return authors.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addAuthor(Author author) {
        authors.add(author);
    }

    @Override
    public void deleteAuthor(int id) {
        authors.remove(getById(id));
    }
}
