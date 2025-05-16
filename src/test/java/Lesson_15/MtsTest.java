package Lesson_15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MtsTest {
    WebDriver driver;
    WebDriverWait wait;
    private static final String Site_url = "https://www.mts.by/";
    private static final String Phone = "(29)777-77-77";

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(Site_url);

        // Закрываем окно с cookie, если оно появляется
        try {
            WebElement acceptCookie = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(., 'Принять')]")));
            acceptCookie.click();
        } catch (TimeoutException ignored) {
        }
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(., 'Онлайн пополнение') and contains(., 'без комиссии')]")));

        String actualText = title.getText()
                .replace("\n", " ")
                .replaceAll("\\s+", " ")
                .trim();

        assertEquals("Онлайн пополнение без комиссии", actualText,
                "Заголовок не соответствует ожидаемому");
    }

    @Test
    void testLink() {
        WebElement testLink = driver.findElement(By.cssSelector(
                "div.pay__wrapper a[href*='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']"));

        assertTrue(testLink.isDisplayed(), "Ссылка не отображается");
        assertTrue(testLink.isEnabled(), "Ссылка не активна");
        assertTrue(testLink.getAttribute("href").endsWith(
                        "/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"),
                "Ссылка ведет не на ожидаемый URL");

        testLink.click();

        // Ждем, пока URL изменится на ожидаемый (для уверенности, что страница загрузилась)
        wait.until(ExpectedConditions.urlContains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"));

        // Прокрутка к заголовку (если нужно)
        By headingLocator = By.xpath("//h3[contains(text(),'Оплата банковской картой')]");

        // Дополнительное ожидание видимости заголовка
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(headingLocator));

        // Скроллим к элементу, чтобы гарантировать видимость
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", heading);

        assertEquals("Оплата банковской картой", heading.getText(), "Текст заголовка не совпадает");
    }

    @Test
    void testLogo() {
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
                            (src != null && src.toLowerCase().contains(logoName.toLowerCase()));
                });
    }

    @Test
    void testButton() {
        WebElement serviceTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(@class, 'select__now') and text()='Услуги связи']")
        ));
        serviceTab.click();

        // Ввод номера телефона
        WebElement phoneInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input#connection-phone")
        ));
        phoneInput.clear();
        phoneInput.sendKeys(Phone);

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
}
