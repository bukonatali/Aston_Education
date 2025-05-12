package Lesson_17;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PostFormDataTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private static HttpClient httpClient;
    private static final String URL = "https://postman-echo.com/post";

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
        //  HttpClient для отправки запросов
        httpClient = HttpClient.newHttpClient();
    }

    @Test
    void testPostFormDataResponseBodyAndStatusCode() throws Exception {
        // тело запроса в формате application/x-www-form-urlencoded
        String formData = "foo1=bar1&foo2=bar2";

        // POST запрос с заголовком Content-Type и телом form-data
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(BodyPublishers.ofString(formData))
                .build();

        // отправка запроса и получение ответа
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // проверка кода ответа
        assertEquals(200, response.statusCode(), "Код ответа должен быть 200");

        // Парсим JSON-ответ
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> responseMap = objectMapper.readValue(response.body(), new TypeReference<>() {});

        // проверка, что поля args, data, files, form, headers, json, url совпадают с ожидаемыми значениями

        // ожидаемые значения из условия
        Map<String, Object> expectedArgs = Map.of();
        String expectedData = "";
        Map<String, Object> expectedFiles = Map.of();
        Map<String, String> expectedForm = Map.of("foo1", "bar1", "foo2", "bar2");
        Map<String, Object> expectedJson = Map.of("foo1", "bar1", "foo2", "bar2");
        String expectedUrl = "https://postman-echo.com/post";

        // проверка args
        assertEquals(expectedArgs, responseMap.get("args"), "Поле args не совпадает");

        // проверка data
        assertEquals(expectedData, responseMap.get("data"), "Поле data не совпадает");

        // проверка files
        assertEquals(expectedFiles, responseMap.get("files"), "Поле files не совпадает");

        // проверка form
        assertEquals(expectedForm, responseMap.get("form"), "Поле form не совпадает");

        // проверка json
        assertEquals(expectedJson, responseMap.get("json"), "Поле json не совпадает");

        // проверка url
        assertEquals(expectedUrl, responseMap.get("url"), "Поле url не совпадает");

        // проверка headers
        Map<String, String> headers = (Map<String, String>) responseMap.get("headers");
        assertNotNull(headers, "Поле headers отсутствует");
        assertEquals("postman-echo.com", headers.get("host"), "Заголовок host не совпадает");
        assertEquals("application/x-www-form-urlencoded", headers.get("content-type"), "Заголовок content-type не совпадает");

        }
}
