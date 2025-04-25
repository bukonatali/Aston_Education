package org.example.Lesson_11_1;

public class Lesson_Animal {
    public static void main(String[] args) {
        Cat kokos = new Cat("Kокос");
        Cat murzik = new Cat("Мурзик");

        Dog beni = new Dog("Бэни");
        Dog ron = new Dog("Рон");

        kokos.run(150);  //
        murzik.run(600);   //
        kokos.swim(150);
        murzik.swim(1);

        System.out.println("\n");

        beni.run(500);  //
        ron.run(700);   //
        beni.swim(15);
        ron.swim(9);

        System.out.println("\n");

        System.out.println("Всего животных: " + Animal.getCount());
        System.out.println("Всего собак: " + Dog.getCount());
        System.out.println("Всего котов: " + Cat.getCount());

        System.out.println("\n");


        // Добавляем миски к котам

        Cat[] cats = new Cat[3];
        cats[0] = new Cat();
        cats[1] = new Cat();
        cats[2] = new Cat();

        Bowl bowl = new Bowl(15);
        System.out.println("В миске " + bowl.getFood() + " еды.");

        //Корм котов
        for (Cat cat : cats) {
            cat.eat(bowl, 5); // Каждый кот хочет съесть 8 еды
        }
        System.out.println("В миске осталось " + bowl.getFood() + " еды.");

        // Сытость котов
        for (int i = 0; i < cats.length; i++) {
            System.out.println("Кот " + (i + 1) + " сыт? " + cats[i].isSatiety());
        }

        // Добавить еду в миску
        bowl.addFood(15);
    }
}





