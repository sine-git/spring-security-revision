package com.tuto.spring.firstproject.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDto {
    String accessToken;
    String refreshToken;
}
