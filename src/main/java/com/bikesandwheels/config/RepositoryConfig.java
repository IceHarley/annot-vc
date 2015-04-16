package com.bikesandwheels.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.*;
import org.springframework.orm.jpa.vendor.*;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;
//
@Configuration
@EnableJpaRepositories("com.bikesandwheels.persistence.dao")
@ComponentScan(basePackages = "com.bikesandwheels.persistence.model")
@Profile({"db-file", "db-in-memory"})
@Import(value = { LocalRepositoryConfig.class, InMemoryRepositoryConfig.class })
public class RepositoryConfig {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public EntityManagerFactory entityManagerFactory() {
        if (this.entityManagerFactory == null) {
            LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
            entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
            entityManagerFactory.setPackagesToScan("com.bikesandwheels.persistence.model");
            entityManagerFactory.setDataSource(dataSource);
            entityManagerFactory.setPersistenceUnitName("avc");
            entityManagerFactory.setJpaDialect(jpaDialect());
//            entityManagerFactory.setJpaProperties(jpaProperties());
            entityManagerFactory.afterPropertiesSet();
            this.entityManagerFactory = entityManagerFactory.getObject();
        }
        return entityManagerFactory;
    }

    private JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }

    public JpaDialect jpaDialect() {
        return new HibernateJpaDialect();
    }

//    public Properties jpaProperties() {
//        Properties properties = new Properties();
//        //properties.setProperty("hibernate.hbm2ddl.auto", "create");
//        //properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
//        //properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
//        //properties.setProperty("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
//        //properties.setProperty("hibernate.connection.charSet", environment.getProperty("hibernate.connection.charSet"));
//        //properties.setProperty("hibernate.cache.use_second_level_cache", environment.getProperty("hibernate.cache.use_second_level_cache"));
//        //properties.setProperty("hibernate.cache.use_query_cache", environment.getProperty("hibernate.cache.use_query_cache"));
//        //properties.setProperty("hibernate.cache.use_structured_entries", environment.getProperty("hibernate.cache.use_structured_entries"));
//        //properties.setProperty("hibernate.generate_statistics", environment.getProperty("hibernate.generate_statistics"));
//        return properties;
//    }

    @Autowired
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
