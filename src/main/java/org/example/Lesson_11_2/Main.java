package org.example.Lesson_11_2;


public class Main {
    public static void main(String[] args) {

        // Фигуры
        Circle circle = new Circle(15, "Красный", "Зелёный");
        Rectangle rectangle = new Rectangle(6, 7, "Оранжевый", "Фиолетовый");
        Triangle triangle = new Triangle(5, 7, 9, "Синий", "Желтый");

        // Вывод инфы о фигурах
            circle.printInfo();
            rectangle.printInfo();
            triangle.printInfo();

        }
    }





