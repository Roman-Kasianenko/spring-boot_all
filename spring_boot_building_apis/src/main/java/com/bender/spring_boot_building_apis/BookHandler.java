package com.bender.spring_boot_building_apis;

import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;

public class BookHandler {

    public ServerResponse getAllBooks(ServerRequest request) {
        return ServerResponse.ok().body(
                List.of(new BookRouteConfig.Book("Test book", "author 1"))
        );
    }

    public ServerResponse getBookById(ServerRequest request) {
        Integer id = Integer.valueOf(request.pathVariable("id")); // TODO use it for further filtration
        return ServerResponse.ok().body(
                List.of(new BookRouteConfig.Book("Test book", "author 1"))
        );
    }
}
