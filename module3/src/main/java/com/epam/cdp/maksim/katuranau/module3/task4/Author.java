package com.epam.cdp.maksim.katuranau.module3.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Author {
    private String name;
    private short age;
    private List<Book> bookList;

    public Author(String name, short age) {
        this.name = name;
        this.age = age;
        this.bookList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }

    public short getAge() {
        return age;
    }

    public Author setAge(short age) {
        this.age = age;
        return this;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public Author setBookList(List<Book> bookList) {
        this.bookList = bookList;
        return this;
    }

    @Override
    public String toString() {
        return "Author{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", bookList=" + bookList.stream().map(Book::getTitle).collect(Collectors.toList())
                + '}';
    }
}
