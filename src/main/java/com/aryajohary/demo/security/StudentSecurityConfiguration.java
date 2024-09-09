package com.aryajohary.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class StudentSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails arya = User.builder()
                .username("arya")
                .password("{noop}arya")
                .roles("HOD","Teacher","Student")
                .build();

        UserDetails shishir = User.builder()
                .username("shishir")
                .password("{noop}shishir")
                .roles("Teacher","Student")
                .build();

        UserDetails aniket = User.builder()
                .username("aniket")
                .password("{noop}aniket")
                .roles("Student")
                .build();

        return new InMemoryUserDetailsManager(arya,shishir,aniket);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                configurer -> configurer
                        .requestMatchers(HttpMethod.GET,"/students").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.GET, "/students/{studentId}").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.POST, "/students").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.PUT, "/students/{studentId}").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.DELETE, "/students/{studentId}").hasRole("HOD")
        );

        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
