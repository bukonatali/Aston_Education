package Lesson_17;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeleteRequestTest {

    private final String url = "https://postman-echo.com/delete";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testDeleteRequestResponseBodyAndStatusCode() throws Exception {
        // Текст данных, которые отправим в теле запроса
        String requestBody = "This is expected to be sent back as part of response body.";

        // Создаём HTTP клиент
        HttpClient client = HttpClient.newHttpClient();

        // Формируем DELETE запрос с телом
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .method("DELETE", HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-Type", "text/plain")
                // Явно задаём User-Agent, чтобы получить ожидаемое значение
                .header("User-Agent", "PostmanRuntime/7.43.3")
                .build();

        // Отправляем запрос и получаем ответ
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Проверяем код ответа
        assertEquals(200, response.statusCode(), "Код ответа должен быть 200");

        // Парсим тело ответа в JSON
        JsonNode actualJson = objectMapper.readTree(response.body());

        // Проверка основных полей
        assertTrue(actualJson.has("args"), "В ответе должен быть объект args");
        assertTrue(actualJson.get("args").isEmpty(), "args должен быть пустым");

        assertEquals(requestBody, actualJson.get("data").asText(), "Поле data должно совпадать с отправленным телом");

        assertTrue(actualJson.has("files"), "В ответе должен быть объект files");
        assertTrue(actualJson.get("files").isEmpty(), "files должен быть пустым");

        assertTrue(actualJson.has("form"), "В ответе должен быть объект form");
        assertTrue(actualJson.get("form").isEmpty(), "form должен быть пустым");

        assertTrue(actualJson.has("headers"), "В ответе должен быть объект headers");
        JsonNode headers = actualJson.get("headers");

        // Проверяем заголовок host
        assertTrue(headers.has("host"), "headers должен содержать host");
        assertEquals("postman-echo.com", headers.get("host").asText(), "host должен быть postman-echo.com");

        // Проверяем content-type (частичное совпадение, т.к. может быть кодировка)
        assertTrue(headers.has("content-type"), "headers должен содержать content-type");
        assertTrue(headers.get("content-type").asText().toLowerCase().startsWith("text/plain"), "content-type должен начинаться с text/plain");

        // Проверяем user-agent - он должен быть равен заданному в запросе
        assertTrue(headers.has("user-agent"), "headers должен содержать user-agent");
        assertEquals("PostmanRuntime/7.43.3", headers.get("user-agent").asText(), "user-agent должен быть PostmanRuntime/7.43.3");

        // Проверяем accept - если есть
        if (headers.has("accept")) {
            String accept = headers.get("accept").asText();
            assertNotNull(accept);
            assertFalse(accept.isEmpty());
        }

        // Проверяем cache-control - если есть
        if (headers.has("cache-control")) {
            String cacheControl = headers.get("cache-control").asText();
            assertNotNull(cacheControl);
            assertFalse(cacheControl.isEmpty());
        }

        // Проверяем accept-encoding - если есть
        if (headers.has("accept-encoding")) {
            String acceptEncoding = headers.get("accept-encoding").asText();
            assertNotNull(acceptEncoding);
            assertFalse(acceptEncoding.isEmpty());
        }

        // Заголовок cookie может отсутствовать или быть динамическим - проверим только если есть
        if (headers.has("cookie")) {
            String cookie = headers.get("cookie").asText();
            assertNotNull(cookie);
            assertFalse(cookie.isEmpty());
        }

        // Проверяем, что json равно null
        assertTrue(actualJson.has("json"), "В ответе должен быть ключ json");
        assertTrue(actualJson.get("json").isNull(), "json должен быть null");

        // Проверяем URL
        assertEquals(url, actualJson.get("url").asText(), "url должен совпадать с запросом");
    }
}
