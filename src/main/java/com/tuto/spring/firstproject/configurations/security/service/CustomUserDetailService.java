package com.tuto.spring.firstproject.configurations.security.service;

import com.tuto.spring.firstproject.role.entity.Role;
import com.tuto.spring.firstproject.user.UserRepository;
import com.tuto.spring.firstproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        //List<GrantedAuthority> roles = user.getRole().getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getKey())).collect(Collectors.toUnmodifiableList());
         List<GrantedAuthority> roles =  List.of(new SimpleGrantedAuthority(user.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }
}
