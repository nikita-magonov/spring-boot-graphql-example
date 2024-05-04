package ru.hse.spring.boot.graphql.example.data;

import java.util.Set;

public class Book {

    private String isbn;
    private String name;
    private int pageCount;
    private Set<Integer> authorIds;

    public Book() {
    }

    public Book(String isbn, String name, int pageCount, Set<Integer> authorIds) {
        this.isbn = isbn;
        this.name = name;
        this.pageCount = pageCount;
        this.authorIds = authorIds;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Set<Integer> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(Set<Integer> authorIds) {
        this.authorIds = authorIds;
    }
}
