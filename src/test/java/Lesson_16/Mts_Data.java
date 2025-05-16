package Lesson_16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Mts_Data {
    private final WebDriver driver;
    private final WebDriverWait wait;       // Явное ожидание с таймаутом 15 секунд
    private final WebDriverWait shortWait;  // Явное ожидание с таймаутом 5 секунд

    // Локаторы элементов страницы (используются для поиска элементов на странице)
    private final By cookie = By.xpath("//*[@id='cookie-agree']"); // Кнопка согласия с cookie
    private final By phoneInput = By.xpath("//*[@id='connection-phone']"); // Поле ввода телефона
    private final By sumInput = By.xpath("//*[@id='connection-sum']"); // Поле ввода суммы платежа
    private final By emailInput = By.xpath("//*[@id='connection-email']"); // Поле ввода email
    final By continueButton = By.xpath("//*[@id='pay-connection']/button"); // Кнопка "Продолжить"
    private final By homeInternetTab = By.xpath("//*[@id='pay-section']//div[contains(@class,'home-internet')]//button/span[1]"); // Вкладка "Домашний интернет"
    private final By homeInternetAccountInput = By.xpath("//input[@placeholder='Номер абонента']"); // Поле ввода номера абонента домашнего интернета
    private final By servicesTab = By.xpath("//span[text()='Услуги связи']"); // Вкладка "Услуги связи"
    private final By installmentTab = By.xpath("//button[contains(text(),'Рассрочка')]"); // Вкладка "Рассрочка"
    private final By debtTab = By.xpath("//button[contains(text(),'Задолженность') and @data-open='pay-arrears']"); // Вкладка "Задолженность"

    // вхлдные данные
    public static final String Phone = "(29)777-77-77";
    public static final String Email = "natali@test.com";
    public static final String Sum = "10";

    // Конструктор класса, инициализирует драйвер и объекты ожидания
    public Mts_Data(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));      // Ожидание до
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));  // Короткое ожидание
    }

    public void open() {
        driver.get("https://www.mts.by/");
    }

    public void acceptCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cookie)).click();
        } catch (Exception e) {
            System.out.println("Баннер с файлами cookie не найден: " + e.getMessage());
        }
    }

    // Метод ввода номера телефона в соответствующее поле
    public void enterPhoneNumber(String phone) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(phoneInput));
        element.sendKeys(phone);   // Вводим номер телефона
    }

    // Метод ввода email в соответствующее поле
    public void enterEmail(String email) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        element.sendKeys(email);
    }

    // Метод ввода суммы платежа
    public void enterSum(String sum) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(sumInput));
        element.sendKeys(sum);
    }

    // Метод нажатия на кнопку "Продолжить"
    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    // Метод выбора вкладки "Услуги связи"
    public void selectServicesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(servicesTab)).click();
    }

    // Метод выбора вкладки "Домашний интернет" и ожидание появления поля для ввода номера абонента
    public void selectHomeInternetTab() {
        // Кликаем по вкладке "Домашний интернет"
        wait.until(ExpectedConditions.elementToBeClickable(homeInternetTab)).click();

        // Ожидаем, что поле для ввода номера абонента станет видимым
        wait.until(ExpectedConditions.visibilityOfElementLocated(homeInternetAccountInput));
    }

    // Метод выбора вкладки "Рассрочка"
    public void selectInstallmentTab() {
        wait.until(ExpectedConditions.elementToBeClickable(installmentTab)).click();
    }

    // Метод выбора вкладки "Задолженность"
    public void selectDebtTab() {
        wait.until(ExpectedConditions.elementToBeClickable(debtTab)).click();
    }

    // Метод получения текущего значения из поля ввода телефона
    public String getEnterPhoneNumber() {
        WebElement phoneInputElement = wait.until(ExpectedConditions.presenceOfElementLocated(phoneInput));
        return phoneInputElement.getAttribute("value");
    }

    // Метод получения текущего значения из поля ввода суммы
    public String getEnteredSum() {
        WebElement sumInputElement = wait.until(ExpectedConditions.presenceOfElementLocated(sumInput));
        return sumInputElement.getAttribute("value");
    }

    // Методы получения текста placeholder из полей ввода
    public String getPhoneFieldPlaceholder() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(phoneInput))
                .getAttribute("placeholder");
    }

    public String getSumFieldPlaceholder() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(sumInput))
                .getAttribute("placeholder");
    }

    public String getEmailFieldPlaceholder() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(emailInput))
                .getAttribute("placeholder");
    }

    public String getHomeInternetAccountPlaceholder() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(homeInternetAccountInput))
                .getAttribute("placeholder");
    }


    public PayPage fillAndSubmitPaymentForm(String phone, String amount, String email) {
        open();
        acceptCookies();
        selectServicesTab();
        enterPhoneNumber(phone);
        enterEmail(email);
        enterSum(amount);
        clickContinue();
        return new PayPage(driver);
    }

}
