package io.jh.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders(
                        "Content-Type",
                        "X-Requested-With",
                        "x-session-id",
                        "x-session-info",
                        "x-correlation-id",
                        "x-language-code",
                        "Accept",
                        "Origin",
                        "Authorization",
                        "Date",
                        "Access-Control-Request-Method")
                .exposedHeaders(
                        "Access-Control-Allow-Headers",
                        "Access-Control-Allow-Origin",
                        "Access-Control-Allow-Credentials")
                .allowCredentials(true)
                .maxAge(3000L);
    }
}