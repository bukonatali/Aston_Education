package org.example.Lesson_11_1;

class Cat extends Animal {
    private static int count = 0;
    private boolean satiety;

    public Cat(String name) {
        super(name);
        count++;
    }

    public Cat() {
        count++;
        this.satiety = false; // Изначально кот голоден
    }

    public static int getCount() {
        return count;
    }

    public boolean isSatiety() {
        return satiety;
    }

    public void setSatiety(boolean satiety) {
        this.satiety = satiety;
    }

    @Override
    public void run(int distance) {
        if (distance <= 200) {
            System.out.println(name + " пробежал " + distance + " м");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м. Максимальная дистанция для котов - 200 м.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать.");
       }

    public void eat(Bowl bowl, int food) {
        if (bowl.getFood() >= food) {
            bowl.reduceFood(food);
            this.satiety = true;
            System.out.println("Кот поел из миски.");
        } else {
            System.out.println("В миске недостаточно еды для кота.");
        }
    }
}