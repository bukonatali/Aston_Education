package org.example.Lesson_6;

public class Goods {
    private String name;
    private int production_date;
    private String manufacturer;
    private String country_of_origin;
    private int price;
    private boolean booking_status;

    public Goods(String name, int production_date, String manufacturer,
                 String country_of_origin, int price, boolean booking_status) {
        this.name = name;
        this.production_date = production_date;
        this.manufacturer = manufacturer;
        this.country_of_origin = country_of_origin;
        this.price = price;
        this.booking_status = booking_status;
    }

    public void printInfo() {
        System.out.println("Название:" + " " + name);
        System.out.println("Дата производства:" + " " + production_date);
        System.out.println("Производитель:" + " " + manufacturer);
        System.out.println("Страна происхождения:" + " " + country_of_origin);
        System.out.println("Цена:" + " " + price + " " + "руб.");
        System.out.println("Состояние бронирования покупателем:" + " " + (booking_status ? "Забронирован" : "Не забронирован"));
    }

}



