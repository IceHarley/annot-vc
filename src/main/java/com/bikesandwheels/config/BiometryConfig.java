package com.bikesandwheels.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Конфигурация для биометрических функций
 */
@Component
public class BiometryConfig {

    @Value("${biometry.session.secret:secret}")
    private String sessionSecret;

    /**
     * Получить секрет для биометрической сессии
     * 
     * @return секрет
     */
    public String getSessionSecret() {
        return sessionSecret;
    }
}