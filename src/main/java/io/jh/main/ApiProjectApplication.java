package io.jh.main;

import io.jh.main.config.security.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties({CorsProperties.class})
public class ApiProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiProjectApplication.class, args);
    }
}
