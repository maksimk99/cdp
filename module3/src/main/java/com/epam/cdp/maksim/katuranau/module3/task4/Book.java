package com.epam.cdp.maksim.katuranau.module3.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Book {
    private String title;
    private int numberOfPages;
    private List<Author> authorList;

    public Book(String title, int numberOfPages) {
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.authorList = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public Book setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
        return this;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public Book setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
        return this;
    }

    @Override
    public String toString() {
        return "Book{"
                + "title='" + title + '\''
                + ", numberOfPages=" + numberOfPages
                + ", authorList=" + authorList.stream().map(Author::getName).collect(Collectors.toList())
                + '}';
    }
}
