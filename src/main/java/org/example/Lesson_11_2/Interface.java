package org.example.Lesson_11_2;

// Интерфейс фигур
interface Shape {
    double calculatePerimeter(); // Метод периметра
    double calculateArea(); // Метод площади
    String getFillColor(); // Метод цвета заливки
    String getBorderColor(); // Метод цвета границы


    // Дефолтный
    default void printInfo() {
        System.out.println("Периметр: " + calculatePerimeter());
        System.out.println("Площадь: " + calculateArea());
        System.out.println("Цвет заливки: " + getFillColor());
        System.out.println("Цвет границы: " + getBorderColor());
    }
}


