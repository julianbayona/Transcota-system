package com.transcotech.transcota_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**")
                        .hasAnyAuthority("ROLE_" + Constants.ADMIN_ROLE, "ROLE_" + Constants.USER_ROLE)
                        .requestMatchers("/vehicles/register")
                        .hasAnyAuthority("ROLE_" + Constants.ADMIN_ROLE, "ROLE_" + Constants.USER_ROLE)
                        .requestMatchers("/vehicles/select")
                        .hasAnyAuthority("ROLE_" + Constants.ADMIN_ROLE, "ROLE_" + Constants.USER_ROLE)
                        .requestMatchers("/vehicles/register")
                        .hasAnyAuthority("ROLE_" + Constants.ADMIN_ROLE, "ROLE_" + Constants.USER_ROLE)
                        .requestMatchers("/vehicles/update")
                        .hasAnyAuthority("ROLE_" + Constants.ADMIN_ROLE, "ROLE_" + Constants.USER_ROLE)
                        .requestMatchers("/vehicles/delete")
                        .hasAnyAuthority("ROLE_" + Constants.ADMIN_ROLE, "ROLE_" + Constants.USER_ROLE)
                        .requestMatchers("/admin/**").hasAuthority("ROLE_" + Constants.ADMIN_ROLE)
                        .requestMatchers("/user/**").hasAuthority("ROLE_" + Constants.USER_ROLE)
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .successHandler((request, response, authentication) -> {
                            String role = authentication.getAuthorities().toString();
                            if (role.contains("ROLE_" + Constants.ADMIN_ROLE)) {
                                response.sendRedirect("/");
                            } else if (role.contains("ROLE_" + Constants.USER_ROLE)) {
                                response.sendRedirect("/");
                            } else {
                                response.sendRedirect("/");
                            }
                        })
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout"));
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/images/**");
    }
}
