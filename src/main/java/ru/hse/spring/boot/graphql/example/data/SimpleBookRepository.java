package ru.hse.spring.boot.graphql.example.data;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class SimpleBookRepository implements BookRepository {

    private List<Book> books = new ArrayList<>(List.of(
            new Book("978-5-17-092624-4", "Двенадцать стульев", 416, Set.of(1, 2)),
            new Book("978-5-17-148949-6", "Вишневый сад", 208, Set.of(3))
    ));

    @Override
    public Book getByIsbn(String isbn) {
        return books.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }
    @Override
    public void saveBook(Book book) {
        books.add(book);
    }

    @Override
    public void deleteBook(String isbn) {
        books.remove(getByIsbn(isbn));
    }
}
