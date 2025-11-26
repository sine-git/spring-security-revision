package com.tuto.spring.firstproject.configurations.security.exceptionhandling;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthorizationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //String path = request.getServletPath();

        String jsonResponse = """
        {
            "status": 401,
            "error": "Unauthorized",
            "message": "The user is not authenticated yet.",
            "path": "%s",
            "timestamp": "%s"
        }
        """.formatted(
                request.getRequestURI(),
                java.time.Instant.now().toString()
        );
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        //response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(jsonResponse);
        //response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unautorized");
    }
}
