package com.asiewiera.externalapp.client;

import java.util.List;

public interface BooksClient {
     List<String> readBooks();
     void addBook(String name, String token);

}
