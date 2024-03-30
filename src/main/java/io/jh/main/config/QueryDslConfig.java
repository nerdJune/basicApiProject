package io.jh.main.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryDslConfig {

    @PersistenceContext(unitName = "mainEntityManager")
    EntityManager mainEntityManager;

    @Bean(name = "mainQueryFactory")
    public JPAQueryFactory mainQueryFactory() {
        return new JPAQueryFactory(mainEntityManager);
    }
}
