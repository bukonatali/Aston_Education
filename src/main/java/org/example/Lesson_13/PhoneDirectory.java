package org.example.Lesson_13;

import java.util.*;

public class PhoneDirectory {
    private final Map<String, List<String>> directory = new TreeMap<>();

    public void add(String lastName, String phoneNumber) {
        directory.computeIfAbsent(lastName, k -> new ArrayList<>()).add(phoneNumber);
    }

    public List<String> get(String lastName) {
        return directory.getOrDefault(lastName, Collections.emptyList());
    }

    public void printAllEntries() {
        System.out.println("\nПолный телефонный справочник:");
        directory.forEach((name, phones) ->
                System.out.println(name + ": " + String.join(", ", phones)));
    }

    public static void demo() {
        PhoneDirectory phoneBook = new PhoneDirectory();

        phoneBook.add("Наталья", "000-00-01");
        phoneBook.add("Настя", "000-00-02");
        phoneBook.add("Влад", "000-00-03");
        phoneBook.add("Илья", "000-00-04");
        phoneBook.printAllEntries();

        System.out.println("\nПоиск по имени:");
        searchAndPrint(phoneBook, "Вера");
        searchAndPrint(phoneBook, "Настя");
        searchAndPrint(phoneBook, "Влад");
        }

    private static void searchAndPrint(PhoneDirectory phoneBook, String lastName) {
        List<String> phones = phoneBook.get(lastName);
        if (phones.isEmpty()) {
            System.out.println("Абонент " + lastName + " не найден");
        } else {
            System.out.println(lastName + ": " + String.join(", ", phones));
        }
    }
}