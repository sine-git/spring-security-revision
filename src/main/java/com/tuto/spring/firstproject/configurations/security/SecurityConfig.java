package com.tuto.spring.firstproject.configurations.security;


import com.tuto.spring.firstproject.configurations.security.exceptionhandling.CustomAccessDeniedHandler;
import com.tuto.spring.firstproject.configurations.security.exceptionhandling.CustomAuthorizationEntryPoint;
import com.tuto.spring.firstproject.configurations.security.service.CustomUserDetailService;
import com.tuto.spring.firstproject.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.
                csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable()).
                sessionManagement(sessionManagement->{
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                }).
                authorizeHttpRequests(request-> {
                    //request.requestMatchers("/user/free/**").permitAll();
                   // request.requestMatchers("/user/secure/**").authenticated();

                   // request.requestMatchers("/user/secure/**").hasRole("ADMIN")
                            request.anyRequest().permitAll();
                }).
                formLogin(formLogin->{
                    formLogin.disable();
                }).
                httpBasic(httpSecurityHttpBasicConfigurer ->{
                    httpSecurityHttpBasicConfigurer.authenticationEntryPoint(new CustomAuthorizationEntryPoint());
                    //httpSecurityHttpBasicConfigurer.disable();
                } ).
                exceptionHandling(exceptions -> {
                    exceptions.accessDeniedHandler(new CustomAccessDeniedHandler());
                }).
                build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
            return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    //@Bean
    //AuthenticationManager authenticationManager(){
    //AuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    //return ;
    //}
    


    @Bean AuthenticationProvider authenticationManager(CustomUserDetailService userDetailsService, PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

}
