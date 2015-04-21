package com.bikesandwheels.config;

import com.bikesandwheels.interactors.Scanner;
import org.springframework.context.annotation.*;

@Configuration
@Profile({Profiles.LIVE, Profiles.DB_FILE, Profiles.DB_IN_MEMORY})
public class ScannerConfig {
    @Bean
    public Scanner scanner() {
        return new Scanner();
    }
}
