package org.example.Lesson_11_1;

class Animal {
    private static int count = 0;
    protected String name;

    public Animal(String name) {
        this.name = name;
        count++;
    }

    public Animal() {
        count++;
    }

    public static int getCount() {
        return count;
    }

    public void run(int distance) {
        System.out.println("Животное пробежало " + distance + " м.");
    }

    public void swim(int distance) {
        System.out.println("Животное проплыло " + distance + " м.");
    }
}
