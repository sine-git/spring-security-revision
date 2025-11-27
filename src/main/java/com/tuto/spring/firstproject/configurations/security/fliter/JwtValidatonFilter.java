package com.tuto.spring.firstproject.configurations.security.fliter;

import com.tuto.spring.firstproject.configurations.security.exceptionhandling.CustomAuthorizationEntryPoint;
import com.tuto.spring.firstproject.configurations.security.service.CustomUserDetailsService;
import com.tuto.spring.firstproject.configurations.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtValidatonFilter extends OncePerRequestFilter {
    final JwtService jwtService;
    final CustomUserDetailsService userDetailsService;
    final CustomAuthorizationEntryPoint customAuthorizationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
        String authorisationHeader = request.getHeader("Authorization").toString();
        String jwt = authorisationHeader.substring(7);
        boolean tokenExpiration = jwtService.isTokenExpired(jwt);
        if(tokenExpiration){
            customAuthorizationEntryPoint.commence(request, response, new BadCredentialsException("Token is expired"));
            return ;
        }

                    ;
        String username = jwtService.exractUsername(jwt);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            e.printStackTrace();
            customAuthorizationEntryPoint.commence(request, response, new BadCredentialsException("Invalid token"));
            return;

        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/auth/login");
    }
}
