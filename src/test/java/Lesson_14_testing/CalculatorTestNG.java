package Lesson_14_testing;
import org.example.Lesson_14_testing.Calculator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class CalculatorTestNG {

    @DataProvider(name = "operationsData")
    public Object[][] createData() {
        return new Object[][]{
                {5, 2, 7, 3, 10, 2.5},
                {10, 5, 15, 5, 50, 2.0},
                {0, 1, 1, -1, 0, 0.0},
                {-8, -4, -12, -4, 32, 2.0}
        };
    }

    @Test(dataProvider = "operationsData")
    public void testAddition(int a, int b, int expectedSum, int expectedSub, int expectedMul, double expectedDiv) {
        assertEquals(Calculator.add(a, b), expectedSum);
    }

    @Test(dataProvider = "operationsData")
    public void testSubtraction(int a, int b, int expectedSum, int expectedSub, int expectedMul, double expectedDiv) {
        assertEquals(Calculator.subtract(a, b), expectedSub);
    }

    @Test(dataProvider = "operationsData")
    public void testMultiplication(int a, int b, int expectedSum, int expectedSub, int expectedMul, double expectedDiv) {
        assertEquals(Calculator.multiply(a, b), expectedMul);
    }

    @Test(dataProvider = "operationsData")
    public void testDivision(int a, int b, int expectedSum, int expectedSub, int expectedMul, double expectedDiv) {
        assertEquals(Calculator.divide(a, b), expectedDiv, 0.0001);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivisionByZero() {
        Calculator.divide(1, 0);
    }
}











