package com.bikesandwheels.config;

import com.bikesandwheels.interactors.AnnotatedClassesSearcher;
import com.bikesandwheels.interactors.annotated_classes_searcher.*;
import com.bikesandwheels.tools.HierarchyAnnotatedClassesSearcher;
import org.hsqldb.jdbc.JDBCDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
public class TestConfig extends Config {
    @Bean
    @Override
    public AnnotatedClassesSearcher annotatedClassesSearcher() {
        return new HierarchyAnnotatedClassesSearcher();
    }

    @Bean
    public PathAnnotatedClassesSearcher pathAnnotatedClassesSearcher() {
        return new PathAnnotatedClassesSearcher();
    }

    @Bean
    @Qualifier("Hierarchy")
    public HierarchyAnnotatedClassesSearcher hierarchyAnnotatedClassesSearcher() {
        return new HierarchyAnnotatedClassesSearcher();
    }

    @Override
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        JDBCDataSource dataSource = new JDBCDataSource();
        dataSource.setUrl("jdbc:hsqldb:mem:avc-test");
        dataSource.setDatabaseName("AVC-TEST");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        return dataSource;
    }
}
