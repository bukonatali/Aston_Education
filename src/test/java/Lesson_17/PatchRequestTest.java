package Lesson_17;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class PatchRequestTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    public void testPatchRequestResponseBodyAndStatusCode() {
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // проверка тела ответа
        assertTrue(response.jsonPath().getMap("args").isEmpty(), "args должен быть пустым");
        assertEquals(requestBody, response.jsonPath().getString("data"), "Поле data не совпадает");
        assertTrue(response.jsonPath().getMap("files").isEmpty(), "files должен быть пустым");
        assertTrue(response.jsonPath().getMap("form").isEmpty(), "form должен быть пустым");
        assertNull(response.jsonPath().get("json"), "json должен быть null");
        assertEquals("https://postman-echo.com/patch", response.jsonPath().getString("url"), "URL не совпадает");

        // Получаем заголовки из JSON и приводим ключи к нижнему регистру
        Map<String, String> headers = response.jsonPath().getMap("headers");
        Map<String, String> lowerCaseHeaders = headers.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey().toLowerCase(),
                        Map.Entry::getValue
                ));

        // проверка обязательных заголовков и их значения

        assertEquals("postman-echo.com", lowerCaseHeaders.get("host"), "host не совпадает");

        assertTrue(lowerCaseHeaders.containsKey("connection"), "connection должен присутствовать");
        assertNotNull(lowerCaseHeaders.get("connection"));
        assertFalse(lowerCaseHeaders.get("connection").isEmpty());

        assertTrue(lowerCaseHeaders.containsKey("content-type"), "content-type должен присутствовать");
        assertNotNull(lowerCaseHeaders.get("content-type"));
        assertTrue(lowerCaseHeaders.get("content-type").toLowerCase().startsWith("text/plain"), "content-type должен начинаться с text/plain");

        assertTrue(lowerCaseHeaders.containsKey("user-agent"), "user-agent должен присутствовать");
        assertNotNull(lowerCaseHeaders.get("user-agent"));
        assertFalse(lowerCaseHeaders.get("user-agent").isEmpty());

        assertTrue(lowerCaseHeaders.containsKey("accept"), "accept должен присутствовать");
        assertNotNull(lowerCaseHeaders.get("accept"));
        assertFalse(lowerCaseHeaders.get("accept").isEmpty());

        // Проверяем наличие заголовка cache-control. а его нет, то вывела предупреждение
        if (lowerCaseHeaders.containsKey("cache-control")) {
            String cacheControl = lowerCaseHeaders.get("cache-control");
            assertNotNull(cacheControl, "cache-control не должен быть null");
            assertFalse(cacheControl.isEmpty(), "cache-control не должен быть пустым");
        } else {
            System.out.println("Заголовок cache-control отсутствует в ответе, проверка пропущена.");
        }

        assertTrue(lowerCaseHeaders.containsKey("accept-encoding"), "accept-encoding должен присутствовать");
        assertNotNull(lowerCaseHeaders.get("accept-encoding"));
        assertFalse(lowerCaseHeaders.get("accept-encoding").isEmpty());
    }
}
