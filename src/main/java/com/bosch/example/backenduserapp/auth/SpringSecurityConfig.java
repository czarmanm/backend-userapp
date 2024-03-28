package com.bosch.example.backenduserapp.auth;

import com.bosch.example.backenduserapp.auth.filters.JwtAuthenticationFilter;
import com.bosch.example.backenduserapp.auth.filters.JwtValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
public class SpringSecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    public SpringSecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers(HttpMethod.GET, "/users").permitAll();
                    registry.requestMatchers(HttpMethod.GET, "/users/{id}").hasAnyRole("USER", "ADMIN");
                    registry.requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN");
                    registry.requestMatchers("/users/**").hasRole("ADMIN");
                    // registry.requestMatchers(HttpMethod.PUT, "/users/{id}").hasRole("ADMIN");
                    // registry.requestMatchers(HttpMethod.DELETE, "/users/{id}").hasRole("ADMIN");
                    registry.requestMatchers(toH2Console()).permitAll();
                    registry.anyRequest().authenticated();
                })
                .addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationConfiguration.getAuthenticationManager()))
                .csrf(configurer -> {
                    configurer.disable();
                    configurer.ignoringRequestMatchers(toH2Console());
                })
                .headers(configurer ->
                        configurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .sessionManagement(management ->
                        management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
