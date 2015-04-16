package com.bikesandwheels.config;

import org.hsqldb.jdbc.JDBCDataSource;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
@Profile({"db-file"})
public class LocalRepositoryConfig {
    @Bean
    public DataSource dataSource() {
        JDBCDataSource dataSource = new JDBCDataSource();
        dataSource.setUrl("jdbc:hsqldb:file:hsqldb/avcdb");
        dataSource.setUser("SA");
        dataSource.setPassword("");
        return dataSource;
    }
}
