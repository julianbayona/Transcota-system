package com.transcotech.transcota_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {
    
    @Bean
    public UserDetailsService userDetailsService(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder){
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder.encode("user"))
            .roles(Constants.USER_ROLE)
            .build();

        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("admin"))
            .roles(Constants.ADMIN_ROLE)
            .build();

        UserDetails anotherUser = User.builder()
            .username("other")
            .password(passwordEncoder.encode("other"))
            .roles(Constants.OTHER_ROLE)
            .build();

        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user, anotherUser, admin);

        return username -> {
            if ("admin".equals(username)) {
                return inMemoryUserDetailsManager.loadUserByUsername(username);
            } else {
                return customUserDetailsService.loadUserByUsername(username);
            }
        };
    }
}
