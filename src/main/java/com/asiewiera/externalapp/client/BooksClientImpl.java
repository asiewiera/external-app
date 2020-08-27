package com.asiewiera.externalapp.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PropertySource("classpath:application.properties")
@Component
public class BooksClientImpl implements BooksClient {

    private RestTemplate restTemplate = new RestTemplate();

    @Value(value = "${books.server.address}")
    private String booksServerAddress = "http://localhost:7000/spring-client1/api/books";

    public void setBooksServerAddress(String booksServerAddress) {
        this.booksServerAddress = booksServerAddress;
    }

    public String getBooksServerAddress() {
        return booksServerAddress;
    }

    @Override
    public List<String> readBooks(){
         ResponseEntity<List<String>> responseEntity= restTemplate.exchange(booksServerAddress,
                 HttpMethod.GET,
                 HttpEntity.EMPTY,
                 new ParameterizedTypeReference<List<String>>() {});
        return responseEntity.getBody();
    }


    @Override
    public void addBook(String name, String token){
        MultiValueMap<String, String> headersMap = new LinkedMultiValueMap<>();
        headersMap.put("Authorization", Collections.singletonList(token));
        HttpEntity<String> httpEntity = new HttpEntity<>(name, new HttpHeaders(headersMap));
        restTemplate.exchange(
                booksServerAddress,
                HttpMethod.POST,
                httpEntity,
                String.class);
    }

}
