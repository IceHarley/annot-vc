package com.bikesandwheels.config;

import com.bikesandwheels.persistence.service.*;
import com.bikesandwheels.persistence.service.impl.*;
import org.springframework.context.annotation.*;

@Configuration
@Profile({Profiles.DB_FILE, Profiles.DB_IN_MEMORY})
public class ServiceConfig {
    @Bean
    public AuthorService authorService() {
        return new AuthorServiceImpl();
    }

    @Bean
    public ClassService classService() {
        return new ClassServiceImpl();
    }
}
