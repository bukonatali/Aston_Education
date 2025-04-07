package org.example.Lesson_6;

public class Product {
    private String name;
    private String date;
    private String manufacturer;
    private String countryOfOrigin;
    private int price;
    private boolean bookingStatus;

    public Product(String name, String date, String manufacturer,
                   String countryOfOrigin, int price, boolean bookingStatus) {
        this.name = name;
        this.date = date;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.bookingStatus = bookingStatus;
    }

    public void printProductInfo() {
        System.out.println("Название:" + " " + name);
        System.out.println("Дата производства:" + " " + date);
        System.out.println("Производитель:" + " " + manufacturer);
        System.out.println("Страна происхождения:" + " " + countryOfOrigin);
        System.out.println("Цена:" + " " + price + " " + "руб.");
        System.out.println("Состояние бронирования покупателем:" + " " + (bookingStatus ? "Забронирован" : "Не забронирован"));
    }
}
