package com.tuto.spring.firstproject.configurations.security.exceptionhandling;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        String jsonResponse = """
        {
            "status": 403,
            "error": "Forbidden",
            "message": "The user is authenticated but doesn't have the right to access the ressource.",
            "path": "%s",
            "timestamp": "%s"
        }
        """.formatted(
                request.getRequestURI(),
                java.time.Instant.now().toString()
        );
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write(jsonResponse);
        //response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unautorized");


    }
}
