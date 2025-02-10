package com.bender.spring_boot_building_apis;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        System.out.println("Logging Filter");
        req.getHeaderNames().asIterator().forEachRemaining(requestHeader -> {
            System.out.println(requestHeader + ": " + req.getHeader(requestHeader) );
        });

        String bender = req.getHeader("Bender");
        if (bender != null && bender.equals("reject")) {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }

        chain.doFilter(request, response);
    }
}
