package org.example.Lesson_14_junit_5;

public class Triangle {
    public static double triangleArea(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) throw new IllegalArgumentException("Стороны должны быть положительными");
        double s = (a + b + c) / 2.0;
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
        if (Double.isNaN(area)) throw new IllegalArgumentException("Недопустимые значения для сторон треугольника");
        return area;
    }
}
