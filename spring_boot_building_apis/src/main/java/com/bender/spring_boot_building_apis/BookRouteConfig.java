package com.bender.spring_boot_building_apis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.GET;

@Configuration
public class BookRouteConfig {

    record Book(String title, String author) {
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        BookHandler bookHandler = new BookHandler();
        return RouterFunctions
                .route()
                .GET( "/api/v1/books", bookHandler::getAllBooks)
                .GET( "/api/v1/books/{id}", bookHandler::getBookById)
                .build();
    }
}
