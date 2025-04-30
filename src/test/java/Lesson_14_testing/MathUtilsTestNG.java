package Lesson_14_testing;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.example.Lesson_14_testng.MathUtils;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class MathUtilsTestNG {

    @DataProvider(name = "validFactorialData")
    public Object[][] provideValidFactorialData() {
        return new Object[][]{
                {0, 1L, "Факториал 0 должен быть 1"},
                {1, 1L, "Факториал 1 должен быть 1"},
                {5, 120L, "Факториал 5 должен быть 120"}
        };
    }

    @DataProvider(name = "invalidFactorialData")
    public Object[][] provideInvalidFactorialData() {
        return new Object[][]{
                {-5, "Число не может быть отрицательным"}
        };
    }

    @Test(dataProvider = "validFactorialData", description = "Проверка корректного вычисления факториала")
    public void testValidFactorials(int input, long expected, String description) {
        assertEquals(MathUtils.factorial(input), expected, description);
    }

    @Test(dataProvider = "invalidFactorialData", expectedExceptions = IllegalArgumentException.class)
    public void testInvalidFactorials(int input, String expectedMessage) {
        try {
            MathUtils.factorial(input);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), expectedMessage);
            return; // Выход из метода, если исключение поймано и проверено
        }
        fail("Ожидалось исключение IllegalArgumentException");
    }
}
