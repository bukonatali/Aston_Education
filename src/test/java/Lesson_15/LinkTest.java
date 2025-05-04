package Lesson_15;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LinkTest {

    WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void testLink() {
        // главна страница mts
        driver.get("https://mts.by");

        // смотреть Devtools Elements
        var payBlock = driver.findElement(By.cssSelector("div.pay__wrapper"));

        //  смотреть Devtools Elements
        var serviceLink = payBlock.findElement(
                By.xpath(".//a[contains(@href, '/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/')]"));

        // проверка ссылки
        Assertions.assertTrue(serviceLink.isDisplayed());
        Assertions.assertTrue(serviceLink.isEnabled());
        Assertions.assertTrue(serviceLink.getAttribute("href")
                .endsWith("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"));
    }

    //закрыть хром
    @AfterEach
    void teardown() {
        driver.quit();
    }
}





