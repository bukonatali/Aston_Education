package Lesson_14_junit_5;

import org.example.Lesson_14_junit_5.Triangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    @DisplayName("Первый тест: валидные данные ")
    @Test
    void testTriangleAreaValid() {
        assertEquals(6, Triangle.triangleArea(3, 4, 5), 0.01);
    }

    @DisplayName("Второй тест: несуществующие стороны ")
    @Test
    void testTriangleAreaInvalidSides() {
        assertThrows(IllegalArgumentException.class, () -> Triangle.triangleArea(3, 1, 22));
    }

    @DisplayName("Третий тест: отрицательные стороны ")
    @Test
    void testTriangleAreaNegativeSide() {
        assertThrows(IllegalArgumentException.class, () -> Triangle.triangleArea(-4, 5, 6));
    }
}
