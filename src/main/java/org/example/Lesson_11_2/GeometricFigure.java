package org.example.Lesson_11_2;

// Интерфейс для геометрических фигур
interface GeometricFigure {
    double calculatePerimeter();

    double calculateArea(); // площадь
    String getFillColor(); // цвет заливки
    void setFillColor(String color); // установка цвета заливки
    String getBorderColor(); // цвет границы
    void setBorderColor(String color); // установка цвета границы
}




