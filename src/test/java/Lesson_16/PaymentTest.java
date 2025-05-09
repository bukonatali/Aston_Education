package Lesson_16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class PaymentTest {
    private WebDriver driver;
    private PaymentPage paymentPage;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // Автоматическая настройка chromedriver
        WebDriverManager.chromedriver().setup();

        // Отключение уведомлений браузера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("https://mts.by");

        // Инициализация явного ожидания
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Инициализация PageObject, передаём WebDriver
        paymentPage = new PaymentPage(driver);

    }

    @Test(description = "Проверка корректности процесса оплаты через сервисы")
    public void testConnectionPaymentFlow() {
        // Выбираем услугу "Услуги связи"
        paymentPage.selectServicesOption();

        // телефон
        paymentPage.fillPhone("297777777");

        // сумму
        paymentPage.fillSum("10");

        // кнопка "Продолжить"
        paymentPage.clickContinue();

        // Проверяем, что сумма в попапе отображается корректно
        Assert.assertTrue(paymentPage.isPopupSumCorrect(), "Popup с суммой не отображается корректно");

        // Проверяем, что кнопка "Оплатить" неактивна
        Assert.assertTrue(paymentPage.isPayButtonDisabled(), "Кнопка Оплатить не должна быть активной");

        // Проверяем корректность отображения номера телефона с кодом страны
        Assert.assertTrue(paymentPage.isPhoneDisplayedCorrectly("375297777777"), "Телефон отображается некорректно");

        // Проверяем наличие надписи "Номер карты"
        Assert.assertTrue(paymentPage.isCardNumberLabelPresent(), "Метка 'Номер карты' не отображается");

        // Проверяем, что иконки платежных систем отображаются (кроме Maestro, который скрыт)
        Assert.assertTrue(paymentPage.arePaymentIconsPresent(), "Иконки платежных систем не все отображаются");

        // Проверяем, что иконка Maestro скрыта
        Assert.assertTrue(paymentPage.isMaestroIconHidden(), "Иконка Maestro должна быть скрыта");
    }

    @AfterMethod
    public void tearDown() {
        // Закрываем браузер после теста
        if (driver != null) {
            driver.quit();
        }
    }
}

