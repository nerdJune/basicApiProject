package io.jh.main.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan
@EnableJpaRepositories(
        basePackages = {"io.jh.main.repository"},
        entityManagerFactoryRef = "mainEntityManagerFactory",
        transactionManagerRef = "mainTransactionManager")
public class MainTransactionManagerConfig {

    @Value("${spring.data.jpa.show-sql}")
    private boolean isShowsql;

    @Value("${spring.data.jpa.database-platform}")
    private String databasePlatfrom;

    @Value("${spring.data.jpa.hibernate.use-new-id-generator-mappings}")
    private String useNewIdGeneratorMappings;

    @Value("${spring.data.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    @Value("${spring.data.jpa.properties.hibernate.jdbc.batch_size}")
    private String jdbcBatchSize;

    @Value("${spring.data.jpa.properties.hibernate.format_sql}")
    private String formatSql;

    @Value("${spring.data.jpa.properties.hibernate.default_batch_fetch_size}")
    private String defaultBatchFetchSize;

    @Value("${spring.data.jpa.properties.hibernate.query.in_clause_parameter_padding}")
    private String useInClauseParameterPadding;

    @Value("${spring.data.jpa.properties.hibernate.allow_update_outside_transaction}")
    private String allowUpdateOutsideTransaction;

    @Primary
    @Bean(name = "mainEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mainEntityManagerFactory(
            @Qualifier("mainDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource);
        emfb.setPersistenceProvider(new HibernatePersistenceProvider());
        emfb.setPersistenceUnitName("mainEntityManager");
        emfb.setPackagesToScan("io.jh.main.model");
        Properties properties = new Properties();
        properties.setProperty("hibernate.id.new_generator_mappings", useNewIdGeneratorMappings);
        properties.setProperty("hibernate.hbm2ddl.auto", ddlAuto);
        properties.setProperty(
                "hibernate.physical_naming_strategy",
                CamelCaseToUnderscoresNamingStrategy.class.getName());
        properties.setProperty("hibernate.jdbc.batch_size", jdbcBatchSize);
        properties.setProperty(
                "hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        properties.setProperty("hibernate.format_sql", formatSql);
        properties.setProperty("hibernate.default_batch_fetch_size", defaultBatchFetchSize);
        properties.setProperty(
                "hibernate.query.in_clause_parameter_padding", useInClauseParameterPadding);
        properties.setProperty(
                "hibernate.allow_update_outside_transaction", allowUpdateOutsideTransaction);

        emfb.setJpaProperties(properties);
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(isShowsql);
        jpaVendorAdapter.setGenerateDdl(false);
        jpaVendorAdapter.setDatabasePlatform(databasePlatfrom);

        emfb.setJpaVendorAdapter(jpaVendorAdapter);
        return emfb;
    }

    @Primary
    @Bean(name = "mainTransactionManager")
    public PlatformTransactionManager mainTransactionManager(
            @Qualifier("mainEntityManagerFactory")
            EntityManagerFactory mainEntityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mainEntityManagerFactory);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor
    mainExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
