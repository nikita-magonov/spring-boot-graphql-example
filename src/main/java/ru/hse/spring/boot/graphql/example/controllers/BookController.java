package ru.hse.spring.boot.graphql.example.controllers;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import ru.hse.spring.boot.graphql.example.data.Author;
import ru.hse.spring.boot.graphql.example.data.AuthorRepository;
import ru.hse.spring.boot.graphql.example.data.Book;
import ru.hse.spring.boot.graphql.example.data.BookRepository;
import java.util.Random;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class BookController {

    private static int greatestAuthorId = 3;
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

    @MutationMapping
    public Author addAuthor(@Argument String firstName, @Argument String lastName) {
        int id = ++greatestAuthorId;
        Author author = new Author(id, firstName, lastName);
        authorRepository.addAuthor(author);
        return authorRepository.getById(id);
    }

    @MutationMapping
    public Author deleteAuthor(@Argument int id) {
        Author author = authorRepository.getById(id);
        authorRepository.deleteAuthor(id);
        return author;
    }

    @MutationMapping
    public Book createBook(@Argument String name, @Argument Set<Integer> authors, @Argument int pageCount) {
        String isbn = generateISBN();
        Book book = new Book(isbn, name, pageCount, authors);
        bookRepository.saveBook(book);
        return bookByIsbn(isbn);
    }

    @MutationMapping
    public Book deleteBook(@Argument String isbn) {
        Book book = bookRepository.getByIsbn(isbn);
        bookRepository.deleteBook(isbn);
        return book;
    }

    public static String generateISBN() {
        Random random = new Random();

        StringBuilder isbn = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int digit = random.nextInt(10);
            isbn.append(digit);
        }

        int checksum = calculateChecksum(isbn.toString());
        isbn.append(checksum);

        return isbn.toString();
    }

    private static int calculateChecksum(String isbn) {
        int sum = 0;
        for (int i = 0; i < isbn.length(); i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        int checksum = 10 - (sum % 10);
        return (checksum == 10) ? 0 : checksum;
    }
}
