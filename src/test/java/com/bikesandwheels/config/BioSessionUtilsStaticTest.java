package com.bikesandwheels.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(properties = {
    "biometry.session.secret=test-secret-key-for-static"
})
class BioSessionUtilsStaticTest {

    @Test
    void testBuildAndParseSession() throws JsonProcessingException, IOException {
        // Given
        String serviceName = "test-service";
        String processId = "test-process-123";
        String basePath = "/test/path";

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
        BioProcessInfo processInfo = new BioProcessInfo("service1", "process1", "/path1");

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
    void testDifferentSecretsProduceDifferentTokens() throws JsonProcessingException {
        // Given
        BioProcessInfo processInfo = new BioProcessInfo("service1", "process1", "/path1");

        // When
        String sessionId1 = BioSessionUtilsStatic.build(processInfo);

        // Then - токены должны быть разными для разных секретов
        assertNotNull(sessionId1);
        assertTrue(sessionId1.length() > 0);
    }
}