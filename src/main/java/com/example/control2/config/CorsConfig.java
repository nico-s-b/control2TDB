package com.example.control2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Permite que el frontend se conecte a este backend.
        // Como es una lista, puedes colocar varios orígenes permitidos.
        // Ten en consideración que el puerto puede variar dependiendo del frontend.
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:8090"));

        // Permite que se puedan hacer peticiones con los métodos que tengas dentro de la lista
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));

        // Permite que se puedan enviar los headers que tengas dentro de la lista
        // Por lo general se coloca "*", que significa que se pueden enviar todos los headers
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));

        // Permite que el Frontend accede al Authorization para el token
        corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

}