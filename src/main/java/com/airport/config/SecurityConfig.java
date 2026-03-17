package com.airport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
        .csrf(csrf -> csrf.disable())

        .authorizeHttpRequests(auth -> auth

            .requestMatchers("/login","/css/**","/images/**").permitAll()

            .requestMatchers("/add","/delete/**")
            .hasRole("ADMIN")

            .requestMatchers("/book","/saveBooking")
            .hasAnyRole("ADMIN","STAFF","USER")

            .requestMatchers("/view","/viewBookings")
            .hasAnyRole("ADMIN","STAFF","USER")

            .anyRequest().authenticated()
        )

        .formLogin(login -> login
            .loginPage("/login")
            .successHandler((request,response,authentication)->{

                var roles = authentication.getAuthorities().toString();

                if(roles.contains("ADMIN")){
                    response.sendRedirect("/adminDashboard");
                }
                else if(roles.contains("STAFF")){
                    response.sendRedirect("/staffDashboard");
                }
                else{
                    response.sendRedirect("/userDashboard");
                }

            })
            .permitAll()
        )

        .logout(logout -> logout
            .logoutSuccessUrl("/login")
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}