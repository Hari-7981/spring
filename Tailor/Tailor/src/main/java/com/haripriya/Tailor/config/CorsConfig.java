//package com.haripriya.Tailor.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//@Configuration
//public class CorsConfig { // Renamed from SecurityConfig to clarify its purpose if only handling CORS here
//
//   // Adjust package as needed
//
//
//        public void addCorsMappings(CorsRegistry registry) {
//            registry.addMapping("/api/**") // Apply CORS to all paths under /api/
//                    .allowedOrigins("http://localhost:4200") // Your Angular app's origin
//                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow these HTTP methods
//                    .allowedHeaders("*") // Allow all headers
//                    .allowCredentials(true) // Allow sending cookies/authorization headers
//                    .maxAge(3600); // How long the preflight response can be cached (in seconds)
//        }
//    }
