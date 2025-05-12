package Lesson_17;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PostRawTextTest {
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

    private static final String URL = "https://postman-echo.com/post";
    private static final String REQUEST_BODY = "This is expected to be sent back as part of response body.";

    @Test
    void postRawText_shouldReturnExpectedResponse() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type", "text/plain")
                .header("Accept", "text/plain")
                .POST(HttpRequest.BodyPublishers.ofString(REQUEST_BODY))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode(), "Код ответа должен быть 200");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualJson = mapper.readTree(response.body());

        assertEquals(REQUEST_BODY, actualJson.get("data").asText(), "Поле data не совпадает");
        assertEquals(URL, actualJson.get("url").asText(), "Поле url не совпадает");

        assertTrue(actualJson.get("args").isEmpty(), "Поле args должно быть пустым");
        assertTrue(actualJson.get("files").isEmpty(), "Поле files должно быть пустым");
        assertTrue(actualJson.get("form").isEmpty(), "Поле form должно быть пустым");
        assertTrue(actualJson.get("json").isNull(), "Поле json должно быть null");

        JsonNode headers = actualJson.get("headers");
        assertNotNull(headers, "Поле headers отсутствует");

        assertEquals("postman-echo.com", headers.get("host").asText(), "Заголовок host не совпадает");
        assertEquals("text/plain", headers.get("content-type").asText(), "Заголовок content-type не совпадает");

        assertTrue(headers.has("user-agent"), "Заголовок user-agent отсутствует");
        assertTrue(headers.has("accept"), "Заголовок accept отсутствует");
    }
}
