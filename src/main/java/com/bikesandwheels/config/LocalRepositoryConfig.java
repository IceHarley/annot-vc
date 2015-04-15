package com.bikesandwheels.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.*;

import javax.sql.DataSource;

@Configuration
@Profile({"db-local", "db"})
public class LocalRepositoryConfig {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .addScript("classpath:database-schema.sql")
                .setType(EmbeddedDatabaseType.HSQL)
                .setName("avc")
                //.addScript("/hsqldb/database-data.sql")
                .build();
    }
}
