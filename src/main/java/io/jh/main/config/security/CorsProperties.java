package io.jh.main.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {
    private String allowedOriginPatterns;
    private String allowedMethods;
    private String allowedHeaders;
    private String allowCredentials;
    private Long maxAge;
}
