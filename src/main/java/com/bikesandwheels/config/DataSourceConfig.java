package com.bikesandwheels.config;

import org.hsqldb.jdbc.JDBCDataSource;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    public static final String PROPERTY_DB_URL = "db.url";
    public static final String PROPERTY_DB_USERNAME = "db.username";
    public static final String PROPERTY_DB_PASSWORD = "db.password";
    public static final String PROPERTY_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    public static final String PROPERTY_HIBERNATE_DIALECT = "hibernate.dialect";
    public static final String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    public static final String PROPERTY_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    public static final String PROPERTY_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";

    @Profile(Profiles.DB_FILE)
    @Configuration
    @PropertySource("classpath:application.properties")
    static class ApplicationProperties {}

    @Profile(Profiles.DB_IN_MEMORY)
    @Configuration
    @PropertySource("classpath:test.properties")
    static class TestProperties {}

    @Bean
    public DataSource dataSource(Environment environment) {
        JDBCDataSource dataSource = new JDBCDataSource();
        dataSource.setUrl(environment.getProperty(PROPERTY_DB_URL));
        dataSource.setUser(environment.getProperty(PROPERTY_DB_USERNAME));
        dataSource.setPassword(environment.getProperty(PROPERTY_DB_PASSWORD));
        return dataSource;
    }

    @Bean
    public Properties jpaProperties(Environment environment) {
        Properties properties = new Properties();
        properties.setProperty(PROPERTY_HIBERNATE_HBM2DDL_AUTO, environment.getProperty(PROPERTY_HIBERNATE_HBM2DDL_AUTO));
        properties.setProperty(PROPERTY_HIBERNATE_DIALECT, environment.getProperty(PROPERTY_HIBERNATE_DIALECT));
        properties.setProperty(PROPERTY_HIBERNATE_SHOW_SQL, environment.getProperty(PROPERTY_HIBERNATE_SHOW_SQL));
        properties.setProperty(PROPERTY_HIBERNATE_FORMAT_SQL, environment.getProperty(PROPERTY_HIBERNATE_FORMAT_SQL));
        properties.setProperty(PROPERTY_HIBERNATE_NAMING_STRATEGY, environment.getProperty(PROPERTY_HIBERNATE_NAMING_STRATEGY));
        return properties;
    }
}