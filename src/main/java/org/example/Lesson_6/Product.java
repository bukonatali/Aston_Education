package org.example.Lesson_6;

public class Product {
    private String name;
    private String LocalDate;
    private String manufacturer;
    private String country_of_origin;
    private int price;
    private boolean booking_status;

    public Product(String name, String LocalDate, String manufacturer,
                   String country_of_origin, int price, boolean booking_status) {
        this.name = name;
        this.LocalDate = LocalDate;
        this.manufacturer = manufacturer;
        this.country_of_origin = country_of_origin;
        this.price = price;
        this.booking_status = booking_status;
    }

    public void printProductInfo() {
        System.out.println("Название:" + " " + name);
        System.out.println("Дата производства:" + " " + LocalDate);
        System.out.println("Производитель:" + " " + manufacturer);
        System.out.println("Страна происхождения:" + " " + country_of_origin);
        System.out.println("Цена:" + " " + price + " " + "руб.");
        System.out.println("Состояние бронирования покупателем:" + " " + (booking_status ? "Забронирован" : "Не забронирован"));
    }
}
