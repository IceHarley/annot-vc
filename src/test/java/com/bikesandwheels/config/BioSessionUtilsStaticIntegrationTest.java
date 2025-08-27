package com.bikesandwheels.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
    "biometry.session.secret=integration-test-secret"
})
class BioSessionUtilsStaticIntegrationTest {

    @Test
    void testBuildAndParseWithTestSecret() throws JsonProcessingException, IOException {
        // Given
        BioProcessInfo processInfo = new BioProcessInfo("integration-service", "integration-process", "/integration/path");

        // When
        String sessionId = BioSessionUtilsStatic.build(processInfo);
        BioProcessInfo parsedInfo = BioSessionUtilsStatic.parse(sessionId);

        // Then
        assertNotNull(sessionId);
        assertNotNull(parsedInfo);
        assertEquals(processInfo.getServiceName(), parsedInfo.getServiceName());
        assertEquals(processInfo.getProcessId(), parsedInfo.getProcessId());
        assertEquals(processInfo.getBasePath(), parsedInfo.getBasePath());
    }

    @Test
    void testTokenFormat() throws JsonProcessingException {
        // Given
        BioProcessInfo processInfo = new BioProcessInfo("test-service", "test-process", "/test/path");

        // When
        String sessionId = BioSessionUtilsStatic.build(processInfo);

        // Then - JWT токен должен содержать 3 части, разделенные точками
        assertNotNull(sessionId);
        String[] parts = sessionId.split("\\.");
        assertEquals(3, parts.length, "JWT token should have 3 parts");
        
        // Проверяем, что все части не пустые
        for (String part : parts) {
            assertFalse(part.isEmpty(), "JWT token part should not be empty");
        }
    }

    @Test
    void testSameInputProducesSameToken() throws JsonProcessingException {
        // Given
        BioProcessInfo processInfo1 = new BioProcessInfo("service1", "process1", "/path1");
        BioProcessInfo processInfo2 = new BioProcessInfo("service1", "process1", "/path1");

        // When
        String sessionId1 = BioSessionUtilsStatic.build(processInfo1);
        String sessionId2 = BioSessionUtilsStatic.build(processInfo2);

        // Then - одинаковые входные данные должны производить одинаковые токены
        assertEquals(sessionId1, sessionId2);
    }

    @Test
    void testDifferentInputProducesDifferentTokens() throws JsonProcessingException {
        // Given
        BioProcessInfo processInfo1 = new BioProcessInfo("service1", "process1", "/path1");
        BioProcessInfo processInfo2 = new BioProcessInfo("service2", "process1", "/path1");

        // When
        String sessionId1 = BioSessionUtilsStatic.build(processInfo1);
        String sessionId2 = BioSessionUtilsStatic.build(processInfo2);

        // Then - разные входные данные должны производить разные токены
        assertNotEquals(sessionId1, sessionId2);
    }
}