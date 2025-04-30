package Lesson_14_testing;
import org.example.Lesson_14_testing.Triangle;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TriangleTestNG {

    @DataProvider(name = "triangleAreaDataProvider")
    public Object[][] dataProvider() {
        return new Object[][]{
                {3, 4, 5, 6.0},
                {5, 5, 5, 10.825},
                {7, 8, 9, 26.833}
        };
    }

    @Test(dataProvider = "triangleAreaDataProvider")
    public void testCalculateArea(double a, double b, double c, double expected) {
        double actual = Triangle.triangleArea(a, b, c);
        Assert.assertEquals(actual, expected, 0.01,
                "Ошибка при вычислении площади треугольника со сторонами: " + a + ", " + b + ", " + c);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateAreaNegativeInput() {
        Triangle.triangleArea(-1, 2, 3);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCalculateAreaInvalidTriangle() {
        Triangle.triangleArea(1, 2, 3);
    }
}



