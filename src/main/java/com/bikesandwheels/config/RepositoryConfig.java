package com.bikesandwheels.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.*;
import org.springframework.orm.jpa.vendor.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.bikesandwheels.persistence.repositories")
@EnableTransactionManagement
@ComponentScan(basePackages = "com.bikesandwheels.persistence.model")
@Profile({Profiles.DB_FILE, Profiles.DB_IN_MEMORY})
@Import(value = DataSourceConfig.class)
public class RepositoryConfig {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public EntityManagerFactory entityManagerFactory(Properties jpaProperties) {
        if (this.entityManagerFactory == null) {
            LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
            entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            entityManagerFactory.setPackagesToScan("com.bikesandwheels.persistence.model");
            entityManagerFactory.setDataSource(dataSource);
            entityManagerFactory.setPersistenceUnitName("avc");
            entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
            entityManagerFactory.setJpaProperties(jpaProperties);
            entityManagerFactory.afterPropertiesSet();
            this.entityManagerFactory = entityManagerFactory.getObject();
        }
        return entityManagerFactory;
    }

    @Autowired
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
