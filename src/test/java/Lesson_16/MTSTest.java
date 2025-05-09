package Lesson_16;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class MTSTest extends BaseTest {

    @Test
    public void dropDownList() {
        PageObject pageObject = new PageObject(driver);

        List<String> expectedOptions = Arrays.asList(
                "Услуги связи",
                "Домашний интернет",
                "Рассрочка",
                "Задолженность"
        );

        List<String> actualOptions = pageObject.getOptionsText();

        Assert.assertEquals(actualOptions, expectedOptions, "Опции в выпадающем списке не совпадают");
    }
}



