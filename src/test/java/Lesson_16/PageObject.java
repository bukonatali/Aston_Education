package Lesson_16;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class PageObject {

    private WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Локатор для выпадающего списка
    @FindBy(css = "div.select[style*='z-index: 1']")
    private WebElement selectDiv;

    // Локатор для опций внутри этого div
    @FindBy(css = "div.select[style*='z-index: 1'] option")
    private List<WebElement> options;

    // надписи в полях
    public List<String> getOptionsText() {
        return options.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}





