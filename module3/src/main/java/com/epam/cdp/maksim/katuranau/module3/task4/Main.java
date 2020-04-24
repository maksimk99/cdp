package com.epam.cdp.maksim.katuranau.module3.task4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class Main {
    public static void main(String[] args) {
        Book[] books = createData();
        System.out.println("\nIs any book has more than 200 pages: "
                + Arrays.stream(books).anyMatch(book -> book.getNumberOfPages() > 200));

        System.out.println("\nBook with min number of pages" + Arrays.stream(books)
                .min(comparing(Book::getNumberOfPages)));

        System.out.println("\nBook with max number of pages" + Arrays.stream(books)
                .max(comparing(Book::getNumberOfPages)));

        System.out.println("\nBooks with only single author: " + Arrays.stream(books).parallel()
                .filter(book -> book.getAuthorList().size() == 1).collect(Collectors.toList()));

        System.out.println("\nSort books by number of pages:\n\t"
                + Arrays.stream(books).sorted(comparing(Book::getNumberOfPages)).collect(Collectors.toList()));

        System.out.println("\nSort books by title:\n\t"
                + Arrays.stream(books).sorted(comparing(Book::getTitle)).collect(Collectors.toList()));

        System.out.println("\nList of all titles: "
                + Arrays.stream(books).map(Book::getTitle).collect(Collectors.toList()));

        System.out.println("\nList of all books using forEach method:");
        Arrays.stream(books).forEach(System.out::println);

        System.out.print("\nDistinct list of all authors:\nDuring execution: ");
        List<String> distinctListAuthors = Arrays.stream(books)
                .flatMap(book -> book.getAuthorList().stream().map(Author::getName))
                .peek(author -> System.out.print(author + ", ")).distinct().collect(Collectors.toList());
        System.out.println("\nResult: " + distinctListAuthors);

        Optional<String> titleOfTheBiggestBookOfPushkin = Arrays.stream(books)
                .filter(book -> book.getAuthorList().stream().anyMatch(author -> author.getName().equals("Pushkin")))
                .max(comparing(Book::getNumberOfPages)).map(Book::getTitle);
        Optional<String> titleOfTheBiggestBookOfPrusnikin = Arrays.stream(books)
                .filter(book -> book.getAuthorList().stream().anyMatch(author -> author.getName().equals("Prusnikin")))
                .max(comparing(Book::getNumberOfPages)).map(Book::getTitle);
        System.out.println("\nGet the biggest book of Pushkin: "
                + titleOfTheBiggestBookOfPushkin.orElse("Author don't have any books"));
        System.out.println("Get the biggest book of Prusnikin: "
                + titleOfTheBiggestBookOfPrusnikin.orElse("Author don't have any books"));
    }

    private static Book[] createData() {
        Author[] authors = new Author[]{new Author("Tolstoy", (short) 47), new Author("Pushkin", (short) 32),
                new Author("Lermontov", (short) 29), new Author("Gogol", (short) 45),
                new Author("Esenin", (short) 37), new Author("Prusnikin", (short) 27)};
        Book[] books = new Book[]{new Book("War and Peace", 1100),
                new Book("Childhood", 980),
                new Book("The Captain's Daughter", 860),
                new Book("Eugene Onegin", 770), new Book("Borodino", 10),
                new Book("Dead Souls", 680), new Book("Viy", 430),
                new Book("Anna Snezhina", 560), new Book("Black man", 750)};

        authors[0].setBookList(Arrays.asList(books[0], books[1], books[3]));
        authors[1].setBookList(Arrays.asList(books[2], books[4], books[5]));
        authors[2].setBookList(Arrays.asList(books[3], books[6], books[8]));
        authors[3].setBookList(Arrays.asList(books[1], books[4], books[7]));
        authors[4].setBookList(Arrays.asList(books[0], books[2], books[6]));

        books[0].setAuthorList(Arrays.asList(authors[0], authors[4]));
        books[1].setAuthorList(Arrays.asList(authors[0], authors[3]));
        books[2].setAuthorList(Arrays.asList(authors[1], authors[4]));
        books[3].setAuthorList(Arrays.asList(authors[0], authors[2]));
        books[4].setAuthorList(Arrays.asList(authors[1], authors[3]));
        books[5].setAuthorList(Collections.singletonList(authors[1]));
        books[6].setAuthorList(Arrays.asList(authors[2], authors[4]));
        books[7].setAuthorList(Collections.singletonList(authors[3]));
        books[8].setAuthorList(Collections.singletonList(authors[2]));
        return books;
    }
}
