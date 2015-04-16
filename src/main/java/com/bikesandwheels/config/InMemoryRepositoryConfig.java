package com.bikesandwheels.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.*;

import javax.sql.DataSource;

@Configuration
@Profile({"db-in-memory"})
public class InMemoryRepositoryConfig {
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseFactoryBean bean = new EmbeddedDatabaseFactoryBean();
        bean.setDatabaseType(EmbeddedDatabaseType.HSQL);
        bean.afterPropertiesSet();
        return bean.getObject();
    }
}