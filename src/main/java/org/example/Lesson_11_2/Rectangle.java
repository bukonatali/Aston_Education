package org.example.Lesson_11_2;

class Rectangle implements Shape {
    private double width; // Ширина
    private double height; // Высота
    private String fillColor; // Цвет заливки
    private String borderColor; // Цвет границы

    // Конструктор класса Rectangle
    public Rectangle(double width, double height, String fillColor, String borderColor) {
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    // периметр
    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    // площадь
    @Override
    public double calculateArea() {
        return width * height;
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
        System.out.println("Прямоугольник:");
        Shape.super.printInfo(); //  из интерфейса
        System.out.println();
    }
}