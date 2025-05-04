package Lesson_15;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ButtonTest {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testButton() {
        // главна страница mts
        driver.get("https://www.mts.by");

        // смотреть Devtools Elements id="connection-phone"
        WebElement phoneInput = driver.findElement(By.id("connection-phone"));
        // ввод номера
        phoneInput.sendKeys("297777777");

        // кнопка продолжить
        WebElement continueButton = driver.findElement(By.xpath("//button[contains(@class, 'button__default') and contains(text(), 'Продолжить')]"));
        //
        //assert continueButton.isEnabled() : "Кнопка 'Продолжить' не активна!";

        // клик кнопки
        continueButton.click();

    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

