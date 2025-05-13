package Lesson_15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ButtonTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    void testButton() {
        driver.get("https://www.mts.by");

        // Кликаем кнопку принятия куки, если она есть
        try {
            WebElement cookieAgreeBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("cookie-agree"))
            );
            cookieAgreeBtn.click();
            // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".cookie.show")));
        } catch (TimeoutException e) {
            // Кнопка куки не появилась - продолжаем тест
        }

        // Кликаем вкладку "Услуги связи"
        WebElement serviceTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(@class, 'select__now') and text()='Услуги связи']")
        ));
        serviceTab.click();

        // Ввод номера телефона
        WebElement phoneInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input#connection-phone")
        ));
        phoneInput.clear();
        phoneInput.sendKeys("297777777");

        // Ввод суммы
        WebElement amountInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input#connection-sum")
        ));
        amountInput.clear();
        amountInput.sendKeys("10");

        // Находим кнопку "Продолжить" и проверяем, что она кликабельна
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class, 'button__default') and text()='Продолжить']")
        ));
        assertTrue(submitButton.isEnabled(), "Кнопка 'Продолжить' должна быть активна");

        // Кликаем кнопку "Продолжить"
        submitButton.click();

        // Ждем появления iframe по XPath и проверяем, что он отображается
        WebElement iframeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("/html/body/div[8]/div/iframe")
        ));
        assertTrue(iframeElement.isDisplayed(), "Iframe окно не появилось!");

    }

    @AfterAll
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
