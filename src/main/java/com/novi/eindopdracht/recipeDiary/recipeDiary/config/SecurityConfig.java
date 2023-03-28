package com.novi.eindopdracht.recipeDiary.recipeDiary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        // users --> wordt nog anders!

        UserDetails ud1 = User
                .withUsername("Dennis")
                .password(encoder.encode("1234"))
                .roles("USER")
                .build();

        manager.createUser(ud1);

        UserDetails ud2 = User
                .withUsername("John")
                .password(encoder.encode("5678"))
                .roles("ADMIN")
                .build();

        manager.createUser(ud2);

        return manager;

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http

                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/secret").hasRole("ADMIN")
                .requestMatchers("/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().denyAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();


    }

}
