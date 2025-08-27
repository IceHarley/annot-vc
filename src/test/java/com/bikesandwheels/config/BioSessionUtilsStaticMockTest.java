package com.bikesandwheels.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BioSessionUtilsStaticMockTest {

    @Mock
    private BiometryConfig biometryConfig;

    private BioSessionUtilsStatic bioSessionUtilsStatic;

    @BeforeEach
    void setUp() {
        bioSessionUtilsStatic = new BioSessionUtilsStatic();
        
        // Мокаем конфигурацию
        when(biometryConfig.getSessionSecret()).thenReturn("test-mock-secret");
        
        // Внедряем мок через рефлексию
        ReflectionTestUtils.setField(bioSessionUtilsStatic, "biometryConfig", biometryConfig);
        
        // Вызываем инициализацию
        bioSessionUtilsStatic.init();
    }

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
}