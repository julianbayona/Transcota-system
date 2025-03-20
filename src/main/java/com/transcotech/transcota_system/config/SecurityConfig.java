package com.transcotech.transcota_system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**")
                        .hasAnyAuthority("ROLE_" + Constants.ADMIN_ROLE, "ROLE_" + Constants.DRIVER_ROLE)
                        .requestMatchers("/vehicles/register")
                        .hasAnyAuthority("ROLE_" + Constants.ADMIN_ROLE, "ROLE_" + Constants.DRIVER_ROLE)
                        .requestMatchers("/vehicles/select")
                        .hasAnyAuthority("ROLE_" + Constants.ADMIN_ROLE, "ROLE_" + Constants.DRIVER_ROLE)
                        .requestMatchers("/vehicles/update")
                        .hasAnyAuthority("ROLE_" + Constants.ADMIN_ROLE, "ROLE_" + Constants.DRIVER_ROLE)
                        .requestMatchers("/vehicles/delete")
                        .hasAnyAuthority("ROLE_" + Constants.ADMIN_ROLE, "ROLE_" + Constants.DRIVER_ROLE)
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .successHandler((request, response, authentication) -> {
                            String role = authentication.getAuthorities().toString();
                            if (role.contains("ROLE_" + Constants.ADMIN_ROLE)) {
                                response.sendRedirect("/");
                            } else if (role.contains("ROLE_" + Constants.DRIVER_ROLE)) {
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

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles(Constants.ADMIN_ROLE)
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .roles(Constants.DRIVER_ROLE)
                .build();

        // Configura la autenticación en memoria y desde la base de datos
        auth.inMemoryAuthentication()
                .withUser(admin);
        auth.inMemoryAuthentication()
                .withUser(user);
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/images/**");
    }
}