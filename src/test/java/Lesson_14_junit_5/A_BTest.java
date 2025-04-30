package Lesson_14_junit_5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;


class A_BTest {

    @DisplayName("Числа равны")
    @Test
    void testNumbersAreEqual() {
        int a = 8;
        int b = 8;
        assertEquals(a, b, "Числа должны быть равны");
    }

    @DisplayName("Первое число больше")
    @Test
    void testFirstNumberIsGreater() {
        int a = 10;
        int b = 5;
        assertTrue(a > b, "Первое число должно быть больше второго");
    }

    @DisplayName("Второе число больше")
    @Test
    void testSecondNumberIsGreater() {
        int a = 3;
        int b = 8;
        assertTrue(b > a, "Второе число должно быть больше первого");
    }
}

