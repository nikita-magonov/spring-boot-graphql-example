package ru.hse.spring.boot.graphql.example.data;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Repository
public class SimpleBookRepository implements BookRepository {

    private final List<Book> books = Arrays.asList(
            new Book("978-5-17-092624-4", "Двенадцать стульев", 416, Set.of(1, 2)),
            new Book("978-5-17-148949-6", "Вишневый сад", 208, Set.of(3))
    );

    @Override
    public Book getByIsbn(String isbn) {
        return books.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }
}
