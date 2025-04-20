package org.example.Lesson_11_2;

public class Lesson_Figure {
    public static void main(String[] args) {
        Circle circle = new Circle(5, "Red", "Black");
        Rectangle rectangle = new Rectangle(4, 6, "Blue", "Green");
        Triangle triangle = new Triangle(3, 4, 5, "Yellow", "Purple");

        System.out.println("Круг - Периметр: " + circle.calculatePerimeter() + "; Площадь: " + circle.calculateArea() + "; Цвет заливки: " + circle.getFillColor() + "; Цвет границы: " + circle.getBorderColor());
        System.out.println("Прямоугольник - Периметр: " + rectangle.calculatePerimeter() + "; Площадь: " + rectangle.calculateArea() + "; Цвет заливки: " + rectangle.getFillColor() + "; Цвет границы: " + rectangle.getBorderColor());
        System.out.println("Треугольник - Периметр: " + triangle.calculatePerimeter() + "; Площадь: " + triangle.calculateArea() + "; Цвет заливки: " + triangle.getFillColor() + "; Цвет границы: " + triangle.getBorderColor());
    }
}