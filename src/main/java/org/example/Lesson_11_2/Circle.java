package org.example.Lesson_11_2;

class Circle implements Shape {
    private double radius; // Радиус круга
    private String fillColor; // Цвет заливки
    private String borderColor; // Цвет границы

    // Конструктор класса Circle
    public Circle(double radius, String fillColor, String borderColor) {
        this.radius = radius;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    // периметр (длины окружности)
    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    // площадь круга
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    // цвет заливки
    @Override
    public String getFillColor() {
        return fillColor;
    }

    // цвет границы
    @Override
    public String getBorderColor() {
        return borderColor;
    }

    @Override
    public void printInfo() {
        System.out.println("Круг:");
        Shape.super.printInfo(); // из интерфейса
        System.out.println();
    }
}