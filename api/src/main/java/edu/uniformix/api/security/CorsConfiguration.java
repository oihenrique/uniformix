package edu.uniformix.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                CorsRegistration cors = registry.addMapping("/**");
                cors.allowedOrigins("http://localhost:4200");
                cors.allowedMethods("GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS");
                cors.allowedHeaders("Origin", "Accept", "X-Requested-With", "Content-Type", "Access-Control-Allow-Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization");
                cors.allowCredentials(true);
            }
        };
    }
}
