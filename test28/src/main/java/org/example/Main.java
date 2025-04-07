package org.example;


// Определяем исключения
class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
        super(message);
    }
}

class MyArrayDataException extends Exception {
    public MyArrayDataException(String message) {
        super(message);
    }
}

// Основной класс
public class Main {
    public static void main(String[] args) {
        String[][] array = {
                {"1", "2", "3", "3"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "1"},
                {"13", "14", "15", "16"}
        };

        try {
            int sum = sumArray(array);
            System.out.println("Сумма элементов массива: " + sum);
            System.out.println(array[5][5]);
        } catch (MyArraySizeException e) {
            System.out.println("Ошибка размера массива: " + e.getMessage());
        } catch (MyArrayDataException e) {
            System.out.println("Ошибка данных в массиве: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: выход за пределы массива.");
        }


    }

    public static int sumArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4 || array[0].length != 4) {
            throw new MyArraySizeException("Массив должен быть размером 4x4.");
        }

        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверные данные в ячейке [" + i + "][" + j + "]: " + array[i][j]);
                }
            }
        }

        return sum;
    }


}


