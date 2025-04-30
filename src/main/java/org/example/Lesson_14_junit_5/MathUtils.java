package org.example.Lesson_14_junit_5;

public class MathUtils {
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Не возможен ввод отрицательных чисел");
        }
        if (n == 0) {
            return 1;
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}


