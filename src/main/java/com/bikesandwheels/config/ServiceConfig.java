package com.bikesandwheels.config;

import com.bikesandwheels.persistence.service.AuthorService;
import com.bikesandwheels.persistence.service.impl.AuthorServiceImpl;
import org.springframework.context.annotation.*;

@Configuration
@Profile({Profiles.DB_FILE, Profiles.DB_IN_MEMORY})
public class ServiceConfig {
    @Bean
    AuthorService authorService() {
        return new AuthorServiceImpl();
    }
}
