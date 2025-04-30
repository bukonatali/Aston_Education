package org.example.Lesson_14_testng;

public class A_B {
    public static String compare(int a, int b) {
        if (a == b) {
            return "Числа равны";
        } else if (a > b) {
            return "Первое число больше";
        } else {
            return "Второе число больше";
        }
    }
}
