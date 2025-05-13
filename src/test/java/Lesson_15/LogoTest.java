package Lesson_15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LogoTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testLogo() {
        driver.get("https://mts.by");

        WebElement logosContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".pay__partners")));

        List<WebElement> logoImages = logosContainer.findElements(By.tagName("img"));

        assertAll("Проверка основных логотипов",
                () -> assertTrue(isLogoPresent(logoImages, "Visa")),
                () -> assertTrue(isLogoPresent(logoImages, "Mastercard")),
                () -> assertTrue(isLogoPresent(logoImages, "Белкарт"))
        );
    }

    // Метод для проверки наличия логотипа по атрибуту alt или src
    private boolean isLogoPresent(List<WebElement> logos, String logoName) {
        return logos.stream()
                .anyMatch(img -> {
                    String alt = img.getAttribute("alt");
                    String src = img.getAttribute("src");
                    return (alt != null && alt.contains(logoName)) ||
                            (src != null && src.contains(logoName.toLowerCase()));
                });
    }
}
