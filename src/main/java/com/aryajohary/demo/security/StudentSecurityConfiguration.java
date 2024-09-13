package com.aryajohary.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class StudentSecurityConfiguration {

    @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }


    /***
     * right now, the users are :-
     * ('student_user','{noop}student_pass',1),
     * ('teacher_user','{noop}teacher_pass',1),
     * ('hod_user','{noop}hod_pass',1);
     ***/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                configurer -> configurer
                        .requestMatchers(HttpMethod.GET,"/students")
                        .hasAnyRole("STUDENT","TEACHER","HOD")

                        .requestMatchers(HttpMethod.GET, "/students/{studentId}")
                        .hasAnyRole("STUDENT","TEACHER","HOD")

                        .requestMatchers(HttpMethod.POST, "/students")
                        .hasAnyRole("TEACHER","HOD")

                        .requestMatchers(HttpMethod.PUT, "/students/{studentId}")
                        .hasAnyRole("TEACHER","HOD")

                        .requestMatchers(HttpMethod.DELETE, "/students/{studentId}")
                        .hasAnyRole("HOD")
        );

        httpSecurity.httpBasic(Customizer.withDefaults());

        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }
}
