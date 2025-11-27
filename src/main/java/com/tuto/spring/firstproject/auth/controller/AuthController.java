package com.tuto.spring.firstproject.auth.controller;

import com.tuto.spring.firstproject.auth.dto.AuthRequestDto;
import com.tuto.spring.firstproject.auth.dto.AuthResponseDto;
import com.tuto.spring.firstproject.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    AuthResponseDto login (@RequestBody AuthRequestDto authDto){
        return authService.login(authDto);
    }
}
