package com.tuto.spring.firstproject.auth.service;

import com.tuto.spring.firstproject.auth.dto.AuthRequestDto;
import com.tuto.spring.firstproject.auth.dto.AuthResponseDto;
import com.tuto.spring.firstproject.configurations.security.service.JwtService;
import com.tuto.spring.firstproject.user.UserRepository;
import com.tuto.spring.firstproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthResponseDto login(AuthRequestDto authRequestDto){
        User user = userRepository.findByUsernameAndPassword(authRequestDto.getUsername(), passwordEncoder.encode(authRequestDto.getPassword())).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        final Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("role", user.getRole().getKey());
        claims.put("username", user.getUsername());
        final String jwt = jwtService.generateToken(claims, user);
        AuthResponseDto authResponseDto = AuthResponseDto.builder().accessToken(jwt).refreshToken(jwt).build();
        return authResponseDto;
    }
}
