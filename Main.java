package org.example.Lesson_12;

public class Main {

    public static int processArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr.length != 4 || arr[0].length != 4) {
            throw new MyArraySizeException("Размер массива должен быть 4x4");
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверные данные в ячейке [" + i + "][" + j + "]", e);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        String[][] correctArray = {
                {"10", "12", "15", "0"},
                {"3", "46", "-17", "80"},
                {"1", "8", "44", "1"},
                {"-1", "5", "7", "17"}
        };

        String[][] incorrectSizeArray = {
                {"1", "2"},
                {"3", "4"}
        };

        String[][] incorrectDataArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "не число", "12"},
                {"13", "14", "15", "16"}
        };

        // правильный массив
        try {
            int sum = processArray(correctArray);
            System.out.println("Сумма элементов правильного массива: " + sum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println("Ошибка при обработке правильного массива: " + e.getMessage());
        }

        // неправильный размер
        try {
            int sum = processArray(incorrectSizeArray);
            System.out.println("Сумма элементов массива неверного размера: " + sum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println("Ошибка при обработке массива неверного размера: " + e.getMessage());
        }

        // неправильные данные
        try {
            int sum = processArray(incorrectDataArray);
            System.out.println("Сумма элементов массива с неверными данными: " + sum);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.err.println("Ошибка при обработке массива с неверными данными: " + e.getMessage());
        }

        // ArrayIndexOutOfBoundsException
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[10]); // Попытка доступа к несуществующему элементу
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Ошибка: выход за границы массива: " + e.getMessage());
        }
    }
}




