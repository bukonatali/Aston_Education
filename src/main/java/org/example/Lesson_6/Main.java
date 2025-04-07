package org.example.Lesson_6;

public class Main {
    public static void main(String[] args) {
        Product product1 = new Product("Велосипед Cube Aim EX 29 L",
                "2024", "Cube",
                "Pending System GmbH&Co. Germany",
                3700, true);
        product1.printProductInfo();
        System.out.println("\n");


        Product[] productsArray = new Product[5];
        {
            productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 5599, true);
            productsArray[1] = new Product("Apple Iphone 16 Pro Max 256 Gb", "01.05.2024", "Apple", "China", 4050, false);
            productsArray[2] = new Product("HONOR 200 12GB/256GB", "2024", "Хонор Девайс Компани", "China", 1320, true);
            productsArray[3] = new Product("Samsung Galaxy S25+ SM-S9360", "2025", "Samsung Corp.", "Korea", 2990, false);
            productsArray[4] = new Product("Apple iPhone 15 256GB", "01.01.2023", "Apple", "Korea", 2840, false);
            for (Product product : productsArray) {
                product.printProductInfo();
                System.out.println("\n");
            }

        }

        Park park = new Park("Челюскинцев");
        Park.Attraction attraction = park.new Attraction("Колобок", "11:00 - 21:00", 8);
        park.printAttraction(attraction);
    }
}



