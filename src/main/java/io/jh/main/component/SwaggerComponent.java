package io.jh.main.component;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class SwaggerComponent {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                            .title("JH TEST API")
                            .version("3.0.0")
                            .description("Spring Boot를 이용한 Demo 웹 애플리케이션 API입니다.")
                            .contact(new Contact().name("tistory").url("https://daily-larks.tistory.com/").email("zzzskys@naver.com"));

        SecurityScheme bearerAuth =
                new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .in(SecurityScheme.In.HEADER)
                        .name(HttpHeaders.AUTHORIZATION);

        SecurityRequirement addSecurityItem = new SecurityRequirement();
        addSecurityItem.addList("JWT");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("JWT", bearerAuth))
                .addSecurityItem(addSecurityItem)
                .info(info);
    }
}
