package com.bikesandwheels.main;

import com.bikesandwheels.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.AbstractEnvironment;

public class Application {
    public static void main(String... args) {

        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "live, db-file");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//        ...

        ((AnnotationConfigApplicationContext) context).close();
    }
}
