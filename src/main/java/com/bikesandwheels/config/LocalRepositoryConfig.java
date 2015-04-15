//package com.bikesandwheels.config;
//
//import org.springframework.context.annotation.*;
//import org.springframework.jdbc.datasource.embedded.*;
//import org.springframework.orm.jpa.vendor.Database;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class LocalRepositoryConfig {
//    @Bean
//    public Database database() {
//        return Database.HSQL;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
//                .setName("avc")
////                .addScript("/hsqldb/database-schema.sql")
////                .addScript("/hsqldb/database-data.sql")
//                .build();
//        /*JDBCDataSource dataSource = new JDBCDataSource();
//        dataSource.setUrl("jdbc:hsqldb:file:avc");
//        dataSource.setDatabaseName("avc");
//        dataSource.setDatabase("avc");
//        dataSource.setUser("sa");
//        dataSource.setPassword("");
//        return dataSource;*/
//    }
//}
