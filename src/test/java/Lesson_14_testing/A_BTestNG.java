package Lesson_14_testing;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import org.example.Lesson_14_testing.A_B;

public class A_BTestNG {

    @DataProvider(name = "comparisonData")
    public Object[][] provideComparisonData() {
        return new Object[][]{
                {5, 5, "Числа равны", "5 и 5 должны быть равны"},
                {10, 3, "Первое число больше", "10 должно быть больше 3"},
                {1, 9, "Второе число больше", "1 должно быть меньше 9"},
        };
    }

    @Test(dataProvider = "comparisonData", description = "Проверка сравнения чисел")
    public void testNumberComparison(int a, int b, String expectedResult, String description) {
        assertEquals(A_B.compare(a, b), expectedResult, description);
    }

}


