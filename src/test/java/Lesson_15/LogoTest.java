package Lesson_15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.stream.Collectors;

class LogoTest {
    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
        // главна страница mts
    void testLogo() {
        driver.get("https://mts.by");

        // Локатор из Devtools
        List<WebElement> logos = driver.findElements(
                By.cssSelector("div.pay__partners ul li img")
        );

        // Ожидание
        List<String> expectedAlts = List.of(
                "Visa",
                "Verified By Visa",
                "MasterCard",
                "MasterCard Secure Code",
                "Белкарт"
        );

        // Атрибуты сайта
        List<String> actualAlts = logos.stream()
                .map(e -> e.getAttribute("alt"))
                .collect(Collectors.toList());

        // Проверка присутсвия лого
        Assertions.assertTrue(actualAlts.containsAll(expectedAlts),
                "Не все логотипы платёжных систем найдены на странице");
    }
}
