public class Lesson_4 {

    public static void main(String[] args) {
    printThreeWords1();
    checkSumSign2();
    printColor3();
    compareNumbers4();

    System.out.println(test5(10, -2));
    System.out.println(test5(16, 4));

    checkNumber6(-33);

    System.out.println(test7(7));
    System.out.println(test7(-1));

    printString8("Hello word", 2);

    System.out.println(printYear9(2024));
    System.out.println(printYear9(2025));

    int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
    System.out.println("Test 10: ");
    test10(arr);

    System.out.println("\nTest 11: ");
    test11(100);

    int[] arr2 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
    System.out.println("\nTest 12: ");
    test12(arr2);

    System.out.println("\nTest 13: ");
    test13();

    System.out.println("\nTest 14: ");
    test14(3, 4);
}

    public static void printThreeWords1() {
        System.out.println("Orange,\nBanana,\nApple.");
    }

    public static void checkSumSign2() {
        int x = 5;
        int y = 6;
        int a = x + y;
        if (a >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    public static void printColor3() {
        int value = -12;
        if (value <= 0) {
            System.out.println("Красный");
        }
        else if (value <= 100) {
            System.out.println("Желтый");
        }
        else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers4() {
        int a = 10;
        int b = 20;
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    static boolean test5(int a, int b) {
        return a + b >= 10 && a + b <= 20;
    }


    public static void checkNumber6(int number) {
        if (number >= 0) {
            System.out.println("Число положительное");
        } else {
            System.out.println("Число отрицательное");
        }
    }

    public static boolean test7(int number ) {
        return number < 0;
    }


    public static void printString8(String str, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }

    public static boolean printYear9(int year){
        return year % 100 != 0 && year % 4 == 0 || year % 400 ==0;
    }

    public static void test10(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] > 0)? 0 : 1;
            System.out.print(arr[i] + " ");
        }
    }

    public static void test11(int size) {
        int[] fillArr = new int[size];
        for (int i = 0; i < fillArr.length; i++) {
            fillArr[i] = i + 1;
            System.out.print(fillArr[i] + " ");
        }
    }

    public static void test12(int[] arr2) {
        for (int i = 0; i < arr2.length; i++) {
            if (arr2[i] < 6) {
                arr2[i] = arr2[i] * 2;
            }
            System.out.print(arr2[i] + " ");
        }
    }

    public static void test13() {
        int[][] arr = new int[3][3];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0, x = arr[i].length - 1; j < arr[i].length; j++, x--) {
                if (i == j || i == x) arr[i][j] = 1;
                else arr[i][j] = 0;
                System.out.print(arr[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void test14(int len, int initialValue){
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initialValue;
            System.out.print("[" + i + "]" + arr[i] + " ");
        }
    }
}
