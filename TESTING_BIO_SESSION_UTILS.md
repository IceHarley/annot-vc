# Тестирование BioSessionUtilsStatic

## Обзор

`BioSessionUtilsStatic` - это Spring Bean со статическими методами, что создает некоторые особенности при тестировании. Предлагается несколько подходов к тестированию.

## Способы тестирования

### 1. Spring Boot Test (Рекомендуемый)

**Преимущества:**
- Полная интеграция с Spring контекстом
- Автоматическая инициализация секрета из проперти
- Близко к реальному использованию

**Пример:**
```java
@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(properties = {
    "biometry.session.secret=test-secret-key-for-static"
})
class BioSessionUtilsStaticTest {
    
    @Test
    void testBuildAndParseSession() throws JsonProcessingException, IOException {
        String sessionId = BioSessionUtilsStatic.build("service", "process", "/path");
        BioProcessInfo parsedInfo = BioSessionUtilsStatic.parse(sessionId);
        
        assertNotNull(sessionId);
        assertNotNull(parsedInfo);
    }
}
```

### 2. Unit Test с рефлексией

**Преимущества:**
- Быстрые тесты без Spring контекста
- Полный контроль над секретом
- Изолированность

**Пример:**
```java
class BioSessionUtilsStaticUnitTest {
    
    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(
            BioSessionUtilsStatic.class, 
            "sessionSecret", 
            "unit-test-secret"
        );
    }
    
    @Test
    void testBuildAndParseSession() {
        // тесты...
    }
}
```

### 3. Mock Test

**Преимущества:**
- Контроль над зависимостями
- Возможность тестирования различных сценариев
- Изоляция от внешних зависимостей

**Пример:**
```java
@ExtendWith(MockitoExtension.class)
class BioSessionUtilsStaticMockTest {
    
    @Mock
    private BiometryConfig biometryConfig;
    
    @BeforeEach
    void setUp() {
        when(biometryConfig.getSessionSecret()).thenReturn("test-mock-secret");
        ReflectionTestUtils.setField(bioSessionUtilsStatic, "biometryConfig", biometryConfig);
        bioSessionUtilsStatic.init();
    }
}
```

### 4. Интеграционный тест с профилями

**Преимущества:**
- Тестирование с реальной конфигурацией
- Проверка работы с разными профилями
- Близко к продакшен среде

**Пример:**
```java
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
    "biometry.session.secret=integration-test-secret"
})
class BioSessionUtilsStaticIntegrationTest {
    // тесты...
}
```

## Конфигурация для тестов

### application-test.properties
```properties
biometry.session.secret=test-profile-secret
db.url=jdbc:hsqldb:mem:testdb
hibernate.hbm2ddl.auto=create-drop
```

### Тестовые проперти
```java
@TestPropertySource(properties = {
    "biometry.session.secret=test-secret-key"
})
```

## Важные моменты

### 1. Инициализация секрета
Секрет инициализируется в `@PostConstruct` методе, поэтому:
- В Spring тестах это происходит автоматически
- В unit тестах нужно устанавливать секрет вручную

### 2. Статические методы
Поскольку методы статические:
- Не нужна инъекция бина в тесты
- Можно вызывать напрямую: `BioSessionUtilsStatic.build(...)`
- Секрет хранится в статической переменной

### 3. Изоляция тестов
Для изоляции тестов:
- Используйте разные секреты для разных тестов
- Очищайте статическое состояние между тестами при необходимости

## Примеры тестовых сценариев

### Базовые тесты
```java
@Test
void testBuildAndParseSession() // создание и парсинг токена

@Test
void testInvalidSessionId() // обработка неверного токена

@Test
void testNullInputs() // обработка null значений
```

### Продвинутые тесты
```java
@Test
void testTokenFormat() // проверка формата JWT

@Test
void testSameInputProducesSameToken() // детерминированность

@Test
void testDifferentInputProducesDifferentTokens() // уникальность
```

### Тесты безопасности
```java
@Test
void testDifferentSecretsProduceDifferentTokens() // влияние секрета

@Test
void testTokenValidation() // валидация токенов
```

## Рекомендации

1. **Используйте Spring Boot Test** для интеграционных тестов
2. **Используйте Unit Test** для быстрых тестов логики
3. **Используйте разные секреты** для разных тестовых классов
4. **Тестируйте граничные случаи** (null, пустые строки, неверные токены)
5. **Проверяйте формат JWT** токенов
6. **Тестируйте детерминированность** - одинаковые входные данные должны давать одинаковые токены

## Запуск тестов

```bash
# Все тесты
mvn test

# Только тесты BioSessionUtilsStatic
mvn test -Dtest=*BioSessionUtilsStatic*

# Конкретный тест
mvn test -Dtest=BioSessionUtilsStaticTest#testBuildAndParseSession
```