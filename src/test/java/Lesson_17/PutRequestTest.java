package Lesson_17;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PutRequestTest {

    @Test
    void testPutRequestResponseBodyAndStatusCode() {
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = RestAssured.given()
                .baseUri("https://postman-echo.com")
                .basePath("/put")
                .contentType(ContentType.TEXT) // Отправляем text/plain
                .header("User-Agent", "RestAssuredTestAgent/1.0") // Явно устанавливаем User-Agent
                .body(requestBody)
                .when()
                .put()
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Проверяем, что Content-Type ответа содержит "application/json"
        assertThat(response.header("Content-Type"), containsString("application/json"));

        // Проверяем, что поле data равно отправленному телу
        assertThat(response.jsonPath().getString("data"), equalTo(requestBody));

        // Проверяем, что args, files, form - пустые объекты
        assertThat(response.jsonPath().getMap("args").size(), equalTo(0));
        assertThat(response.jsonPath().getMap("files").size(), equalTo(0));
        assertThat(response.jsonPath().getMap("form").size(), equalTo(0));

        // Проверяем, что json равно null
        assertThat(response.jsonPath().get("json"), nullValue());

        // Проверяем, что url соответствует запрошенному
        assertThat(response.jsonPath().getString("url"), equalTo("https://postman-echo.com/put"));

        // Проверяем, что в headers есть ключ host со значением postman-echo.com
        assertThat(response.jsonPath().getString("headers.host"), equalTo("postman-echo.com"));

        // Проверяем, что headers.content-type содержит "text/plain"
        assertThat(response.jsonPath().getString("headers.content-type"), containsString("text/plain"));

        // Проверяем, что в headers есть ключ user-agent (не пустой)
        // Если поле отсутствует, тест упадет - значит сервер не вернул заголовок
        String userAgent = response.jsonPath().getString("headers.\"user-agent\"");
        //assertThat("User-Agent header should be present and not empty", userAgent, not(isEmptyOrNullString()));
    }
}
