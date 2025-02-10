package com.salesianostriana.dam.proyecto_satapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests((auth) -> auth
                // Solo ADMIN puede modificar y eliminar datos
                .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                // Solo ADMIN puede crear cualquier cosa menos incidencias y notas
                .requestMatchers(HttpMethod.POST, "/incidencias/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.POST, "/notas/**").hasAnyRole("ADMIN", "USER")
                // Cualquier usuario autenticado puede leer datos
                .anyRequest().authenticated());

        return http.build();
    }

}
