package Lesson_16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class PayTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void testPayment() {
        driver.get("https://mts.by");

        // Принятие cookie на баннере
        try {
            WebElement cookieAcceptBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Принять')]")));
            cookieAcceptBtn.click();
            System.out.println("Cookie consent accepted.");
        } catch (Exception e) {
            System.out.println("Cookie consent banner not found, продолжение теста.");
        }

        // Клик по "Услуги связи"
        WebElement connectionServices = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Услуги связи')]")));
        connectionServices.click();
        System.out.println("Кликнули по 'Услуги связи'.");

        // поле "Номер телефона" (id="connection-phone")
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-phone")));
        phoneInput.clear();
        phoneInput.sendKeys("297777777");
        System.out.println("Введён номер телефона: 297777777");

        // поле "Сумма" (id="connection-sum")
        WebElement sumInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-sum")));
        sumInput.clear();
        sumInput.sendKeys("10");
        System.out.println("Введена сумма: 10");

        // кнопка "Продолжить"
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.button__default[type='submit']")));
        continueButton.click();
        System.out.println("Нажата кнопка 'Продолжить'.");

        // Ждать прогрузки iframe и переключиться на него
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("/html/body/div[8]/div/iframe")));
        System.out.println("Переключились на iframe оплаты.");

        // отображения суммы "10.00 BYN"
        WebElement costSpan = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'pay-description__cost')]//span[contains(text(),'10.00 BYN')]")));
        Assert.assertTrue(costSpan.isDisplayed(), "Сумма оплаты отображается некорректно");
        System.out.println("Сумма оплаты '10.00 BYN' отображается корректно.");

        // сумма на кнопке "Оплатить 10.00 BYN"
        WebElement payButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[contains(@class,'colored') and contains(text(),'Оплатить') and contains(text(),'10.00 BYN')]")));
        Assert.assertTrue(payButton.isDisplayed(), "Кнопка оплаты с суммой отображается некорректно");
        System.out.println("Кнопка оплаты с суммой '10.00 BYN' отображается корректно.");

        // текст с номером телефона и услугой
        WebElement paymentDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'Оплата: Услуги связи')]")));
        Assert.assertTrue(paymentDescription.isDisplayed(), "Описание оплаты с номером телефона отсутствует");
        System.out.println("Описание оплаты с номером телефона присутствует.");

        // надпись для реквизитов карты
        WebElement cardNumberLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[contains(text(),'Номер карты')]")));
        Assert.assertTrue(cardNumberLabel.isDisplayed(), "Надпись 'Номер карты' отсутствует");
        System.out.println("Надпись 'Номер карты' отображается.");

        // Проверка наличия иконок платёжных систем
        String[] paymentIcons = {
                "visa-system.svg",
                "mastercard-system.svg",
                "belkart-system.svg",
                "maestro-system.svg",
                //"mir-system-ru.svg"
        };
        for (String icon : paymentIcons) {
            List<WebElement> icons = driver.findElements(By.xpath("//img[contains(@src,'" + icon + "')]"));
            Assert.assertTrue(!icons.isEmpty() && icons.get(0).isDisplayed(),
                    "Иконка платежной системы " + icon + " не найдена или не отображается");
            System.out.println("Иконка платежной системы " + icon + " отображается.");
        }
    }

    private void waitForAndSwitchToFrame(By frameLocator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Браузер закрыт.");
        }
    }
}
