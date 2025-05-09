package Lesson_16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы элементов

    private By servicesDropdown = By.xpath("//button[contains(@class, 'select__header')]");

    // Выбор варианта из списка по тексту
    private By servicesOption = By.xpath("//span[contains(text(),'Услуги связи')]");

    private By phoneInput = By.id("connection-phone");
    private By sumInput = By.id("connection-sum");
    private By continueButton = By.cssSelector("button.button__default[type='submit']");

    // Сумма во всплывающем окне
    private By popupSum = By.xpath("//div[contains(@class,'pay-description__cost')]//span[contains(text(),'10.00 BYN')]");

    // Кнопка Оплатить с суммой
    private By payButton = By.xpath("//button[contains(@class,'colored') and contains(text(),'Оплатить') and contains(text(),'10.00 BYN')]");

    // Отображение номера телефона
    private By phoneDisplay = By.xpath("//span[contains(text(),'Оплата: Услуги связи')]");

    private By cardNumberLabel = By.xpath("//label[contains(text(),'Номер карты')]");

    private By visaIcon = By.cssSelector("img[src*='visa-system.svg']");
    private By mastercardIcon = By.cssSelector("img[src*='mastercard-system.svg']");
    private By belkartIcon = By.cssSelector("img[src*='belkart-system.svg']");
    private By maestroIcon = By.cssSelector("img[src*='maestro-system.svg']");
    private By mirIcon = By.cssSelector("img[src*='mir-system-ru.svg']");

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        // Явное ожидание до 10 секунд
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectServicesOption() {
        // Кликаем по дропдауну, чтобы открыть список
        wait.until(ExpectedConditions.elementToBeClickable(servicesDropdown)).click();
        // Выбираем нужную опцию Услуги связи
        wait.until(ExpectedConditions.elementToBeClickable(servicesOption)).click();
    }

    // заполнить телефон
    public void fillPhone(String phone) {
        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    // заполнить сумму
    public void fillSum(String sum) {
        WebElement sumField = wait.until(ExpectedConditions.visibilityOfElementLocated(sumInput));
        sumField.clear();
        sumField.sendKeys(sum);
    }

    // кнопка Продолжить
    public void clickContinue() {
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueBtn.click();
    }

    //  в окне сумма 10.00 отображается
    public boolean isPopupSumCorrect() {
        WebElement sumElement = wait.until(ExpectedConditions.visibilityOfElementLocated(popupSum));
        return sumElement.isDisplayed();
    }

    // кнопка оплатить не активна
    public boolean isPayButtonDisabled() {
        WebElement payBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(payButton));
        String classAttr = payBtn.getAttribute("class");
        String disabledAttr = payBtn.getAttribute("disabled");
        // Проверяем наличие класса disabled или атрибута disabled
        return (classAttr != null && classAttr.contains("disabled")) || (disabledAttr != null);
    }

    // проверка корректного отображения телефона
    public boolean isPhoneDisplayedCorrectly(String expectedPhone) {
        WebElement phoneSpan = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneDisplay));
        String text = phoneSpan.getText().replaceAll("\\s+", " ").trim().toLowerCase();
        // Проверяем, что текст содержит "оплата: услуги связи" и ожидаемый номер телефона
        return text.contains("оплата: услуги связи") && text.contains(expectedPhone.toLowerCase());
    }

    // проверка наличия подписи поля Номер карты
    public boolean isCardNumberLabelPresent() {
        WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberLabel));
        return label.isDisplayed();
    }

    // проверка иконок
    public boolean arePaymentIconsPresent() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(visaIcon)).isDisplayed() &&
                wait.until(ExpectedConditions.visibilityOfElementLocated(mastercardIcon)).isDisplayed() &&
                wait.until(ExpectedConditions.visibilityOfElementLocated(belkartIcon)).isDisplayed() &&
                wait.until(ExpectedConditions.visibilityOfElementLocated(mirIcon)).isDisplayed();

    }

    // иконка маэстро скрыта
    public boolean isMaestroIconHidden() {
        WebElement maestro = wait.until(ExpectedConditions.presenceOfElementLocated(maestroIcon));
        String opacity = maestro.getCssValue("opacity");
        String display = maestro.getCssValue("display");
        String visibility = maestro.getCssValue("visibility");
        return "0".equals(opacity) || "none".equals(display) || "hidden".equals(visibility);
    }
}


