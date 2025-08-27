# Миграция BioSessionUtils для выноса секрета в проперти

## Проблема
Класс `BioSessionUtils` содержал захардкоженный секрет `SECRET_WORLD = "secret"`, что является небезопасной практикой.

## Решение
Предлагается два варианта решения:

### Вариант 1: Spring Bean (Рекомендуемый)
Использовать `BioSessionUtils` как Spring Bean с инъекцией конфигурации.

#### Изменения в коде:

1. **Добавить секрет в application.properties:**
```properties
#Biometric Session Configuration
biometry.session.secret=your-secure-secret-here
```

2. **Создать конфигурационный класс:**
```java
@Component
public class BiometryConfig {
    @Value("${biometry.session.secret:secret}")
    private String sessionSecret;
    
    public String getSessionSecret() {
        return sessionSecret;
    }
}
```

3. **Модифицировать BioSessionUtils:**
- Убрать `final` и статические методы
- Добавить `@Component` аннотацию
- Внедрить `BiometryConfig` через конструктор
- Использовать `biometryConfig.getSessionSecret()` вместо константы

#### Использование:
```java
@Autowired
private BioSessionUtils bioSessionUtils;

// Вместо BioSessionUtils.build(...)
String sessionId = bioSessionUtils.build(serviceName, processId, basePath);
```

### Вариант 2: Статические методы с инициализацией
Сохранить статические методы, но инициализировать секрет из конфигурации.

#### Изменения:
- Добавить `@PostConstruct` метод для инициализации секрета
- Использовать статическую переменную для хранения секрета
- Остальные методы остаются статическими

#### Использование:
```java
// Использование остается прежним
String sessionId = BioSessionUtilsStatic.build(serviceName, processId, basePath);
```

## Миграция существующего кода

### Поиск использования:
```bash
grep -r "BioSessionUtils\." src/
```

### Замена вызовов:

**Для Варианта 1:**
```java
// Было:
String sessionId = BioSessionUtils.build(serviceName, processId, basePath);
BioProcessInfo info = BioSessionUtils.build(sessionId);

// Стало:
@Autowired
private BioSessionUtils bioSessionUtils;

String sessionId = bioSessionUtils.build(serviceName, processId, basePath);
BioProcessInfo info = bioSessionUtils.parse(sessionId);
```

**Для Варианта 2:**
```java
// Использование остается прежним, но нужно переименовать класс
String sessionId = BioSessionUtilsStatic.build(serviceName, processId, basePath);
BioProcessInfo info = BioSessionUtilsStatic.parse(sessionId);
```

## Безопасность

1. **Никогда не коммитьте реальные секреты в Git**
2. **Используйте переменные окружения для продакшена:**
```properties
biometry.session.secret=${BIOMETRY_SESSION_SECRET:default-secret}
```

3. **Используйте сильные секреты в продакшене** (минимум 32 символа)

## Тестирование

Добавьте тесты для проверки работы с новым секретом:

```java
@Test
public void testSessionBuildWithCustomSecret() {
    // Тест с кастомным секретом
}
```

## Рекомендации

1. **Выберите Вариант 1** для новых проектов - он более гибкий и тестируемый
2. **Используйте Вариант 2** только если критично сохранить статические методы
3. **Обязательно обновите документацию** и примеры использования
4. **Проведите код-ревью** изменений