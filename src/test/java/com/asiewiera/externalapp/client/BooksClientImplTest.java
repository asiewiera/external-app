package com.asiewiera.externalapp.client;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BooksClientImplTest {

    private BooksClient booksClient = new BooksClientImpl();

    @Test
    void shouldReadBooks() {

        List<String> books = booksClient.readBooks();
        books.parallelStream().forEach(System.out::println);


    }

    @Test
    void shouldAddBook() {
        String book = "test ";
        Random random = new Random();
        book+= random.nextInt();
        String token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkFnbmllc3prYSBEb2UiLCJpYXQiOjE1MTYyMzkwMjIsInJvbGUiOiJST0xFX1RFU1RFUiJ9.EqdvrYFsJJlHz4SgJDTiVBVZdC2E1573p-LG_Yj3tho";
        booksClient.addBook(book, token);
    }

    @Test
    void shouldNotAddBook() {
        String book = "test ";
        Random random = new Random();
        book+= random.nextInt();
        String token = "WrongToken";
/*        booksClient.addBook(book, token);*/
    }
}