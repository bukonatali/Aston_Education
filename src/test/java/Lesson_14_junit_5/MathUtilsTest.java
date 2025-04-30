package Lesson_14_junit_5;

import org.example.Lesson_14_junit_5.MathUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MathUtilsTest {
    @DisplayName("Первый тест: факториал 0")
    @Test
    void testFactorialZero(){
            assertEquals(1, MathUtils.factorial(0));
    }


    @DisplayName("Второй тест: факториал положительных чисел")
    @Test
    void testFactorialPositive() {
        assertEquals(40320, MathUtils.factorial(8));
        assertEquals(24, MathUtils.factorial(4));
    }

    @DisplayName("Третий тест: факториал отрицательных чисел")
    @Test
    void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> MathUtils.factorial(-1));
        assertThrows(IllegalArgumentException.class, () -> MathUtils.factorial(-5));
    }
}

