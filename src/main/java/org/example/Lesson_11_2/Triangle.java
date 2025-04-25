package org.example.Lesson_11_2;

class Triangle implements Shape {
    private double side1; // Длина a
    private double side2; // Длина в
    private double side3; // Длина c
    private String fillColor; // Цвет заливки
    private String borderColor; // Цвет границы

    // Конструктор класса Triangle
    public Triangle(double side1, double side2, double side3, String fillColor, String borderColor) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    // периметр
    @Override
    public double calculatePerimeter() {
        return side1 + side2 + side3;
    }

    // площадь
    @Override
    public double calculateArea() {
        double s = calculatePerimeter() / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
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
        System.out.println("Треугольник:");
        Shape.super.printInfo(); //  из интерфейса
        System.out.println();
    }
}