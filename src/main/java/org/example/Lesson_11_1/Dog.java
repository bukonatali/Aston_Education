package org.example.Lesson_11_1;

class Dog extends Animal {
    private static int count = 0;

    public Dog(String name) {
        super(name);
        count++;
    }

    public Dog() {
        count++;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public void run(int distance) {
        if (distance <= 500) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м. Максимальная дистанция для собак - 500 м.");
        }
    }



    @Override
    public void swim(int distance) {
        if (distance <= 10) {
            System.out.println(name + " проплыл " + distance + " м");
        } else {
            System.out.println(name + " не может проплыть " + distance + " м. Максимальная дистанция для собак - 10 м.");
        }
    }
}