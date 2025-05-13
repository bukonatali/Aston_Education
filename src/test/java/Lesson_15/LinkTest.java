package Lesson_15;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LinkTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Увеличено время ожидания
    }

    @Test
    void testLink() {
        driver.get("https://www.mts.by");

        // принять куки, если кнопка есть
        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Принять')]")));
            acceptCookies.click();
        } catch (TimeoutException e) {
            System.out.println("Кнопка 'Принять' не появилась, продолжаем тест.");
        }

        // Находим и кликаем по нужной ссылке
        By serviceLinkLocator = By.cssSelector(
                "div.pay__wrapper a[href*='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']");

        WebElement serviceLink = wait.until(ExpectedConditions.elementToBeClickable(serviceLinkLocator));

        Assertions.assertTrue(serviceLink.isDisplayed(), "Ссылка не отображается");
        Assertions.assertTrue(serviceLink.isEnabled(), "Ссылка не активна");
        Assertions.assertTrue(serviceLink.getAttribute("href").endsWith(
                        "/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"),
                "Ссылка ведет не на ожидаемый URL");

        serviceLink.click();

        // Ждем, пока URL изменится на ожидаемый (для уверенности, что страница загрузилась)
        wait.until(ExpectedConditions.urlContains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"));

        // Прокрутка к заголовку (если нужно)
        By headingLocator = By.xpath("//h3[contains(text(),'Оплата банковской картой')]");

        // Дополнительное ожидание видимости заголовка
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(headingLocator));

        // Скроллим к элементу, чтобы гарантировать видимость
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", heading);

        Assertions.assertEquals("Оплата банковской картой", heading.getText(), "Текст заголовка не совпадает");
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
