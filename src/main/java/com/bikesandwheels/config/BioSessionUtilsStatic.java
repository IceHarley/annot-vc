package com.bikesandwheels.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Утилита для формирования идентификаторов сессии для БСБ
 * Версия с сохранением статических методов и поддержкой конфигурации через проперти
 *
 * @author krutikov
 * @since 13.08.2019
 */
@Component
public class BioSessionUtilsStatic {

    private static final String SESSION_INFO_CLAIM = "session-id-claim";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    private static String sessionSecret = "secret"; // значение по умолчанию

    @Autowired
    private BiometryConfig biometryConfig;

    @PostConstruct
    public void init() {
        sessionSecret = biometryConfig.getSessionSecret();
    }

    private BioSessionUtilsStatic() {
    }

    /**
     * Построение идентификатора сессии для БСБ
     *
     * @param serviceName имя сервиса
     * @param processId   идентификатор процесса
     * @param basePath    базовый путь
     * @return идентификатор сессии для БСБ efrClientSessionId
     * @throws JsonProcessingException ошибка преобразования объекта в строку
     */
    public static String build(String serviceName, String processId, String basePath) throws JsonProcessingException {
        return build(new BioProcessInfo(serviceName, processId, basePath));
    }

    /**
     * Построение идентификатора сессии для БСБ
     *
     * @param processInfo информация о биометрическом процессе
     * @return идентификатор сессии для БСБ efrClientSessionId
     * @throws JsonProcessingException ошибка преобразования объекта в строку
     */
    public static String build(BioProcessInfo processInfo) throws JsonProcessingException {
        return JWT.create()
                .withClaim(SESSION_INFO_CLAIM, OBJECT_MAPPER.writeValueAsString(processInfo))
                .sign(createAlgorithm());
    }

    /**
     * Получение информации о сессии
     *
     * @param efrClientSessionId идентификатор сессии для БСБ
     * @return информация о биометрическом процессе
     * @throws IOException ошибка преобразования строки в объект
     */
    public static BioProcessInfo parse(String efrClientSessionId) throws IOException {
        Claim sessionInfoClaim = JWT.require(createAlgorithm())
                .build()
                .verify(efrClientSessionId)
                .getClaim(SESSION_INFO_CLAIM);
        return OBJECT_MAPPER.readValue(sessionInfoClaim.asString(), BioProcessInfo.class);
    }

    private static Algorithm createAlgorithm() {
        return Algorithm.HMAC512(sessionSecret);
    }
}