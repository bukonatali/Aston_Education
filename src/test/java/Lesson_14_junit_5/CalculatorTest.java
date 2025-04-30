package Lesson_14_junit_5;

import org.example.Lesson_14_junit_5.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calc = new Calculator();

    @DisplayName("Сложение")
    @Test
    void testAdd() {
        assertEquals(12, calc.add(4, 8));
    }

    @DisplayName("Вычитание")
    @Test
    void testSubtract() {
        assertEquals(-4, calc.subtract(9, 13));
    }

    @DisplayName("Умножение")
    @Test
    void testMultiply() {
        assertEquals(9, calc.multiply(3, 3));
    }
    @DisplayName("Умножение на ноль")
    @Test
    void testMultiplyZero() {
        assertEquals(0, calc.multiply(0, 1));
    }

    @DisplayName("Деление")
    @Test
    void testDivide() {
        assertEquals(2, calc.divide(8, 4));
    }

    @DisplayName("Деление на ноль")
    @Test
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> calc.divide(9, 0));
    }
}
