package Lesson_18;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Проверка сайта MTS.by")
@Feature("Проверка блока Онлайн пополнение без комиссии")
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
    @DisplayName("Проверка заголовка 'Онлайн пополнение без комиссии' ")
    @Description("правильное отображение заголовка")
    void verifyBlockTitle() {
        try {
            openPageAndAcceptCookies();

            WebElement title = driver.findElement(By.xpath("//h2[contains(., 'Онлайн пополнение без комиссии')]"));
            String actualText = title.getText()
                    .replace("\n", " ")
                    .replaceAll("\\s+", " ")
                    .trim();
            assertEquals("Онлайн пополнение без комиссии", actualText);
        } catch (Exception e) {
            takeScreenshotOnException();
            throw e;
        }
    }

    @Test
    @DisplayName("Проверка основных логотипов")
    void verifyPaymentLogo() {
        try {
            openPageAndAcceptCookies();

            WebElement logosContainer = driver.findElement(By.cssSelector(".pay__partners"));
            List<WebElement> logoImages = logosContainer.findElements(By.tagName("img"));

            assertAll("Проверка основных логотипов",
                    () -> assertTrue(isLogoPresent(logoImages, "Visa")),
                    () -> assertTrue(isLogoPresent(logoImages, "Mastercard")),
                    () -> assertTrue(isLogoPresent(logoImages, "Белкарт"))
            );
        } catch (Exception e) {
            takeScreenshotOnException();
            throw e;
        }
    }

    @Test
    @DisplayName("Проверка ссылки 'Подробнее о сервисе'")
    void verifyDetailsLink() {
        try {
            openPageAndAcceptCookies();

            WebElement detailsLink = driver.findElement(By.xpath("//a[contains(., 'Подробнее о сервисе')]"));
            assertAll("Проверка ссылки 'Подробнее о сервисе'",
                    () -> assertTrue(detailsLink.isDisplayed()),
                    () -> assertTrue(detailsLink.isEnabled()),
                    () -> assertNotNull(detailsLink.getAttribute("href"))
            );
        } catch (Exception e) {
            takeScreenshotOnException();
            throw e;
        }
    }

    @Test
    @DisplayName("Проверка окна оплаты")
    void verifyPaymentForm() {
        try {
            openPageAndAcceptCookies();
            selectServicesTabStep();
            enterPhoneNumberStep(Mts_Data.Phone);
            enterEmailStep(Mts_Data.Email);
            enterSumStep(Mts_Data.Sum);

            assertTrue(driver.findElement(mtsData.continueButton).isEnabled());
            assertEquals(Mts_Data.Phone, mtsData.getEnterPhoneNumber());
            assertEquals(Mts_Data.Sum, mtsData.getEnteredSum());
        } catch (Exception e) {
            takeScreenshotOnException();
            throw e;
        }
    }

    @Test
    @DisplayName("Проверка placeholders Услуг связи")
    void verifyEmptyFieldsPlaceholdersForMobileServices() {
        try {
            openPageAndAcceptCookies();
            selectServicesTabStep();

            assertAll("Проверка placeholders для Услуг связи",
                    () -> assertEquals("Номер телефона", mtsData.getPhoneFieldPlaceholder(),
                            "Неверный placeholder для номера телефона"),
                    () -> assertEquals("Сумма", mtsData.getSumFieldPlaceholder(),
                            "Неверный placeholder для  суммы"),
                    () -> assertEquals("E-mail для отправки чека", mtsData.getEmailFieldPlaceholder(),
                            "Неверный placeholder для поля email")
            );
        } catch (Exception e) {
            takeScreenshotOnException();
            throw e;
        }
    }

    @Test
    @DisplayName("Проверка диалогового окна оплаты")
    void verifyMobileServicesPaymentProcess() {
        try {
            PayPage paymentPage = fillAndSubmitPaymentFormStep(
                    "(29)777-77-77",
                    "10",
                    "natali@test.com"
            );

            assertAll("Проверка страницы оплаты",
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
        } catch (Exception e) {
            takeScreenshotOnException();
            throw e;
        }
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // @Step шаги

    @Step("Открыть страницу и принять куки")
    private void openPageAndAcceptCookies() {
        mtsData.open();
        mtsData.acceptCookies();
    }

    @Step("Выбрать вкладку 'Услуги'")
    private void selectServicesTabStep() {
        mtsData.selectServicesTab();
    }

    @Step("Ввести номер телефона: {phone}")
    private void enterPhoneNumberStep(String phone) {
        mtsData.enterPhoneNumber(phone);
    }

    @Step("Ввести email: {email}")
    private void enterEmailStep(String email) {
        mtsData.enterEmail(email);
    }

    @Step("Ввести сумму: {sum}")
    private void enterSumStep(String sum) {
        mtsData.enterSum(sum);
    }

    @Step("Заполнить и отправить форму оплаты: телефон={phone}, сумма={sum}, email={email}")
    private PayPage fillAndSubmitPaymentFormStep(String phone, String sum, String email) {
        return mtsData.fillAndSubmitPaymentForm(phone, sum, email);
    }

    // без @Step, так как это утилитарный метод
    private boolean isLogoPresent(List<WebElement> logos, String logoName) {
        return logos.stream().anyMatch(logo -> {
            String altText = logo.getAttribute("alt");
            String src = logo.getAttribute("src");
            return (altText != null && altText.contains(logoName)) ||
                    (src != null && src.toLowerCase().contains(logoName.toLowerCase()));
        });
    }

    // Сохраняем скриншот в папку target и прикрепляем к Allure-отчету
    @Attachment(value = "Скриншот при ошибке", type = "image/png")
    private byte[] takeScreenshotOnException() {
        if (!(driver instanceof TakesScreenshot)) {
            System.err.println("Driver не поддерживает создание скриншотов");
            return new byte[0];
        }
        TakesScreenshot tsDriver = (TakesScreenshot) driver;
        byte[] screenshotBytes = tsDriver.getScreenshotAs(OutputType.BYTES);

        // Сохраняем файл в папку target
        File targetDir = new File("target");
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
        String fileName = "screenshot_" + timestamp + ".png";
        Path targetPath = new File(targetDir, fileName).toPath();

        try {
            Files.write(targetPath, screenshotBytes);
            System.out.println("Скриншот сохранен в: " + targetPath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении скриншота: " + e.getMessage());
        }
        return screenshotBytes;
    }
}
