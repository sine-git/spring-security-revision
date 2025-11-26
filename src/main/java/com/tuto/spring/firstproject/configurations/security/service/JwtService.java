package com.tuto.spring.firstproject.configurations.security.service;

import com.tuto.spring.firstproject.user.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    static final String CLAIM_KEY = "wK3s8dXNvRr2Gs9mZ6tyMw1Q6F4Bd8O1HzgXW/RW5Q0=";
    public <T> T  extractClaim(String token, Function<Claims, T> claimResolver ){
        Claims claims =  extractClaim(token);
        return claimResolver.apply(claims);
    }

    private Claims extractClaim(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJwt(token).getBody();
    }

    private Key getSigningKey() {
        byte [] keyBytes = Decoders.BASE64.decode(CLAIM_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String generateToken(Map<String, Object> claims, User user){
        return Jwts.builder().setClaims(claims).setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    Date exractExpirationDate(String token ){
        return extractClaim(token, Claims::getExpiration);
    }
    String exractUsername(String token ){
        return extractClaim(token, Claims::getSubject);
    }
}
