package Lesson_16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MtsPageTest {
    protected WebDriver driver;
    private Mts_Data mtsData;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupTest() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        mtsData = new Mts_Data(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    void verifyBlockTitle() {
        mtsData.open();
        mtsData.acceptCookies();

        WebElement title = driver.findElement(By.xpath("//h2[contains(., 'Онлайн пополнение без комиссии')]"));
        String actualText = title.getText()
                .replace("\n", " ")
                .replaceAll("\\s+", " ")
                .trim();

        assertEquals("Онлайн пополнение без комиссии", actualText);
    }

    @Test
    void verifyPaymentLogo() {
        mtsData.open();
        mtsData.acceptCookies();

        WebElement logosContainer = driver.findElement(By.cssSelector(".pay__partners"));
        List<WebElement> logoImages = logosContainer.findElements(By.tagName("img"));

        assertAll("Проверка основных логотипов",
                () -> assertTrue(isLogoPresent(logoImages, "Visa")),
                () -> assertTrue(isLogoPresent(logoImages, "Mastercard")),
                () -> assertTrue(isLogoPresent(logoImages, "Белкарт"))
        );
    }

    private boolean isLogoPresent(List<WebElement> logos, String logoName) {
        return logos.stream().anyMatch(logo -> {
            String altText = logo.getAttribute("alt");
            String src = logo.getAttribute("src");
            return (altText != null && altText.contains(logoName)) ||
                    (src != null && src.toLowerCase().contains(logoName.toLowerCase()));
        });
    }

    @Test
    void verifyDetailsLink() {
        mtsData.open();
        mtsData.acceptCookies();

        WebElement detailsLink = driver.findElement(By.xpath("//a[contains(., 'Подробнее о сервисе')]"));

        assertAll("Проверка ссылки 'Подробнее о сервисе'",
                () -> assertTrue(detailsLink.isDisplayed()),
                () -> assertTrue(detailsLink.isEnabled()),
                () -> assertNotNull(detailsLink.getAttribute("href"))
        );
    }

    @Test
    void verifyPaymentForm() {
        mtsData.open();
        mtsData.acceptCookies();
        mtsData.selectServicesTab();
        mtsData.enterPhoneNumber(Mts_Data.Phone);
        mtsData.enterEmail(Mts_Data.Email);
        mtsData.enterSum(Mts_Data.Sum);

        assertTrue(driver.findElement(mtsData.continueButton).isEnabled());
        assertEquals(Mts_Data.Phone, mtsData.getEnterPhoneNumber());
        assertEquals(Mts_Data.Sum, mtsData.getEnteredSum());
    }

    @Test
    void verifyEmptyFieldsPlaceholdersForMobileServices() {
        mtsData.open();
        mtsData.acceptCookies();
        mtsData.selectServicesTab();

        assertAll("Проверка placeholders для Услуг связи",
                () -> assertEquals("Номер телефона", mtsData.getPhoneFieldPlaceholder(),
                        "Неверный placeholder для номера телефона"),
                () -> assertEquals("Сумма", mtsData.getSumFieldPlaceholder(),
                        "Неверный placeholder для  суммы"),
                () -> assertEquals("E-mail для отправки чека", mtsData.getEmailFieldPlaceholder(),
                        "Неверный placeholder для поля email")
        );
    }

    @Test
    void verifyMobileServicesPaymentProcess() {
        PayPage paymentPage = mtsData.fillAndSubmitPaymentForm(
                "(29)777-77-77",
                "10",
                "natali@test.com"
        );

        assertAll("Проверка страницы  оплаты",
                () -> assertTrue(paymentPage.isPaymentFrameDisplayed(),
                        "Платежное окно не отображается"),
                () -> assertEquals("10.00 BYN", paymentPage.getDisplayedSum(),
                        "Неверная сумма"),
                () -> assertTrue(paymentPage.getDisplayedPhoneNumber().contains("375297777777"),
                        "Номер телефона не соответствует ожидаемому"),
                () -> assertEquals("Номер карты", paymentPage.getCardNumberLabel(),
                        "Неверный placeholder для номера карты"),
                () -> assertEquals("Срок действия", paymentPage.getExpiryDateLabel(),
                        "Неверный placeholder срока действия карты"),
                () -> assertEquals("CVC", paymentPage.getCvcLabel(),
                        "Неверный placeholder для CVC"),
                () -> assertEquals(4, paymentPage.getPaymentSystemsCount(),
                        "Неверное количество платежных систем"),
                () -> assertTrue(paymentPage.getSubmitButtonText().contains("10.00 BYN"),
                        "Неверная сумма на кнопке оплаты")
        );


    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}