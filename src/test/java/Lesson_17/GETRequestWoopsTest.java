package Lesson_17;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class GETRequestWoopsTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        // Настройка WebDriverManager для автоматической загрузки chromedriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void tearDown() {
        // Завершение работы браузера после теста
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testGetRequestAndResponseBody() throws Exception {
        // URL для GET-запроса
        String endpoint = "https://postman-echo.com/get?foo1=bar1&foo2=bar2";
        URL url = new URL(endpoint);

        // открыть HTTP-соединение и отправить GET-запрос
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // получить код ответа
        int responseCode = connection.getResponseCode();
        assertEquals(200, responseCode, "Код ответа не равен 200");

        // что в теле ответа
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseBody = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            responseBody.append(line);
        }
        in.close();

        // Ожидаемый JSON-ответ из Postman
        String expectedJson = "{"
                + "\"args\": {"
                + "\"foo1\": \"bar1\","
                + "\"foo2\": \"bar2\""
                + "},"
                + "\"headers\": {"
                + "\"x-forwarded-proto\": \"https\","
                + "\"host\": \"postman-echo.com\","
                + "\"accept\": \"*/*\","
                + "\"accept-encoding\": \"gzip, deflate\","
                + "\"cache-control\": \"no-cache\","
                + "\"postman-token\": \"5c27cd7d-6b16-4e5a-a0ef-191c9a3a275f\","
                + "\"user-agent\": \"PostmanRuntime/7.6.1\","
                + "\"x-forwarded-port\": \"443\""
                + "},"
                + "\"url\": \"https://postman-echo.com/get?foo1=bar1&foo2=bar2\""
                + "}";

        // парсим JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualNode = mapper.readTree(responseBody.toString());
        JsonNode expectedNode = mapper.readTree(expectedJson);

        // проверка полей args и url
        assertEquals(expectedNode.get("args"), actualNode.get("args"), "Поля 'args' не совпадают");
        assertEquals(expectedNode.get("url"), actualNode.get("url"), "Поле 'url' не совпадает");

        // проверка headers
        JsonNode actualHeaders = actualNode.get("headers");
        assertNotNull(actualHeaders, "Поле 'headers' отсутствует в ответе");

        // проверка наличия некоторых важных заголовков (без проверки значений)
        String[] importantHeaders = {"host", "accept", "user-agent"};
        for (String header : importantHeaders) {
            assertTrue(actualHeaders.has(header), "Заголовок '" + header + "' отсутствует");
        }
    }
}
