package com.bikesandwheels.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BioSessionUtilsStaticUnitTest {

    @BeforeEach
    void setUp() {
        // Устанавливаем секрет напрямую через рефлексию
        // Это работает, потому что секрет хранится в статической переменной
        org.springframework.test.util.ReflectionTestUtils.setField(
            BioSessionUtilsStatic.class, 
            "sessionSecret", 
            "unit-test-secret"
        );
    }

    @Test
    void testBuildAndParseSession() throws JsonProcessingException, IOException {
        // Given
        String serviceName = "unit-test-service";
        String processId = "unit-test-process-123";
        String basePath = "/unit/test/path";

        // When
        String sessionId = BioSessionUtilsStatic.build(serviceName, processId, basePath);
        BioProcessInfo parsedInfo = BioSessionUtilsStatic.parse(sessionId);

        // Then
        assertNotNull(sessionId);
        assertNotNull(parsedInfo);
        assertEquals(serviceName, parsedInfo.getServiceName());
        assertEquals(processId, parsedInfo.getProcessId());
        assertEquals(basePath, parsedInfo.getBasePath());
    }

    @Test
    void testBuildWithBioProcessInfo() throws JsonProcessingException, IOException {
        // Given
        BioProcessInfo processInfo = new BioProcessInfo("unit-service1", "unit-process1", "/unit/path1");

        // When
        String sessionId = BioSessionUtilsStatic.build(processInfo);
        BioProcessInfo parsedInfo = BioSessionUtilsStatic.parse(sessionId);

        // Then
        assertNotNull(sessionId);
        assertEquals(processInfo.getServiceName(), parsedInfo.getServiceName());
        assertEquals(processInfo.getProcessId(), parsedInfo.getProcessId());
        assertEquals(processInfo.getBasePath(), parsedInfo.getBasePath());
    }

    @Test
    void testInvalidSessionId() {
        // Given
        String invalidSessionId = "invalid.jwt.token";

        // When & Then
        assertThrows(IOException.class, () -> {
            BioSessionUtilsStatic.parse(invalidSessionId);
        });
    }

    @Test
    void testNullInputs() {
        // Given
        BioProcessInfo nullProcessInfo = new BioProcessInfo(null, null, null);

        // When & Then
        assertDoesNotThrow(() -> {
            String sessionId = BioSessionUtilsStatic.build(nullProcessInfo);
            assertNotNull(sessionId);
        });
    }
}