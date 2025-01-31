package com.hospital.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.hospital.demo.service.CustomUserDetailsService;

import org.springframework.security.core.userdetails.User;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/register").permitAll()
                .requestMatchers("/api/patients/**").hasAnyRole("NURSE", "DOCTOR")
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .userDetailsService(customUserDetailsService)
            .httpBasic(httpBasic -> {})  // New HTTP Basic configuration
            //.formLogin(AbstractHttpConfigurer::disable)  // Disable form login if not needed
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            )
            .csrf(AbstractHttpConfigurer::disable);  // Disable CSRF for API endpoints

        return http.build();
    }

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //     .authorizeHttpRequests(auth -> auth
    //         .requestMatchers("/api/register").permitAll() // Allow registration without aut
    //         .requestMatchers("/api/patients/**").hasAnyRole("NURSE", "DOCTOR")
    //         .requestMatchers("/api/admin/**").hasRole("ADMIN")
    //         .anyRequest().authenticated()
    //     )
    //     .userDetailsService(customUserDetailsService) // Use the custom service
    //         .formLogin()
    //         .and()
    //         .httpBasic();
    //     return http.build();
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails doctor = User.builder()
            .username("doctor")
            .password(passwordEncoder().encode("password"))
            .roles("DOCTOR")
            .build();

        UserDetails nurse = User.builder()
            .username("nurse")
            .password(passwordEncoder().encode("password"))
            .roles("NURSE")
            .build();

        return new InMemoryUserDetailsManager(doctor, nurse);
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails doctor = User.withDefaultPasswordEncoder()
    //         .username("doctor")
    //         .password("password")
    //         .roles("DOCTOR")
    //         .build();
    //     UserDetails nurse = User.withDefaultPasswordEncoder()
    //         .username("nurse")
    //         .password("password")
    //         .roles("NURSE")
    //         .build();
    //     return new InMemoryUserDetailsManager(doctor, nurse);
    // }

}
