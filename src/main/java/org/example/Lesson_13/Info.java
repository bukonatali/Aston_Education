package org.example.Lesson_13;
import java.util.*;

class Info {

    public static void removePoorStudents(Collection<Student> students) {
        students.removeIf(student -> student.getAverageGrade() < 3.0);
    }

    public static void promoteStudents(Collection<Student> students) {
        for (Student student : students) {
            if (student.getAverageGrade() >= 3.0) {
                student.setCourse(student.getCourse() + 1);
            }
        }
    }

    public static void printStudents(Set<Student> students, int course) {
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student);
            }
        }
    }

    public static void main(String[] args) {
        // Создание студентов
        Map<String, Integer> grades1 = new HashMap<>();
        grades1.put("Русский", 5);
        grades1.put("Математика", 5);
        grades1.put("Физика", 5);
        Student student1 = new Student("Наталья", "9-Т", 1, grades1);

        Map<String, Integer> grades2 = new HashMap<>();
        grades2.put("Русский", 2);
        grades2.put("Математика", 2);
        grades2.put("Физика", 4);
        Student student2 = new Student("Настя", "8-Т", 2, grades2);

        Map<String, Integer> grades3 = new HashMap<>();
        grades3.put("Русский", 5);
        grades3.put("Математика", 2);
        grades3.put("Физика", 5);
        Student student3 = new Student("Влад", "10-Т", 1, grades3);

        Map<String, Integer> grades4 = new HashMap<>();
        grades4.put("Русский", 0);
        grades4.put("Математика", 0);
        grades4.put("Физика", 0);
        Student student4 = new Student("Илья", "10-Т", 3, grades4);

        // Создание коллекции студентов
        Set<Student> students = new HashSet<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);


        System.out.println("Исходный список студентов:");
        for (Student student : students) {
            System.out.println(student);
        }

        // Удаление <3 ср. балл
        removePoorStudents(students);
        System.out.println("\nСписок студентов со средним баллом >=3:");
        for (Student student : students) {
            System.out.println(student);
        }

        // Перевод на следующий курс
        promoteStudents(students);
        System.out.println("\nСписок студентов, которые переведены на следующий курс:");
        for (Student student : students) {
            System.out.println(student);
        }

        // 2 курс
        System.out.println("\nСтуденты 2 курса:");
        printStudents(students, 2);

        System.out.println("\nТелефонный справочник:");
        PhoneDirectory.demo();
    }
}


