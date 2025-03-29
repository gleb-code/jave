package org.example;
import java.util.*;


public class Main {
    public static void main(String[] args) {




        printThreeWords(); //задание 1
        checkSumSign();    // задание 2
        printColor();       // задание 3
        compareNumbers();   // задание 4
        System.out.println(isSumInRange(50, -30));  // Пример вызова метода задание 5
        System.out.println(isSumInRange(100, 91)); // Пример вызова метода  задание 5
        System.out.println(isSumInRange(3, -8));  // Пример вызова метода   задание 5
        checkNumber(5);   // Пример вызова метода с положительным числом    задание 6
        checkNumber(-3);  // Пример вызова метода с отрицательным числом     задание 6
        checkNumber(0);    // Пример вызова метода с нулем  задание 6
        System.out.println(isNegative(-5));  // Пример вызова метода с отрицательным числом  задание 7
        System.out.println(isNegative(3));    // Пример вызова метода с положительным числом    задание 7
        System.out.println(isNegative(0));    // Пример вызова метода с нулем   задание 7
        printString("Указанная строка!", 3);  // Пример вызова метода     задание 8
        System.out.println(isLeapYear(2020)); // Пример вызова метода для високосного года  задание 9
        System.out.println(isLeapYear(2021)); // Пример вызова метода для не високосного года   задание 9
        System.out.println(isLeapYear(1900)); // Пример вызова метода для не високосного года   задание 9
        System.out.println(isLeapYear(2000)); // Пример вызова метода для високосного года      задание 9



        // Задаем целочисленный массив задание 10
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        // Печатаем исходный массив
        System.out.print("Исходный массив: ");
        printArray(array);

        // Заменяем 0 на 1 и 1 на 0
        for (int i = 0; i < array.length; i++) {
            // Используем тернарный оператор для замены значений
            array[i] = (array[i] == 0) ? 1 : 0;
        }

        // Печатаем измененный массив
        System.out.print("Измененный массив: ");
        printArray(array);

// Задаем пустой целочисленный массив длиной 100 задание 11
        int[] arrayTask11 = new int[100];

        // Заполняем массив значениями от 1 до 100
        for (int i = 0; i < arrayTask11.length; i++) {
            arrayTask11[i] = i + 1; // Заполняем элемент массива значением i + 1
        }

        // Печатаем заполненный массив
        System.out.print("Заполненный массив: ");
        printArrayTask11(arrayTask11);

        // Задаем массив  задание 12
        int[] arrayTask12 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        // Проходим по массиву и умножаем числа меньше 6 на 2
        for (int i = 0; i < arrayTask12.length; i++) {
            if (arrayTask12[i] < 6) {
                arrayTask12[i] *= 2; // Умножаем на 2
            }
        }

        // Печатаем измененный массив
        System.out.print("Измененный массив: ");
        printArrayTask12(arrayTask12);


        int size = 4; // Размерность массива (количество строк и столбцов) задание 13
        int[][] arrayTask13 = new int[size][size]; // Создаем квадратный массив

        // Заполняем диагональные элементы единицами
        for (int i = 0; i < size; i++) {
            arrayTask13[i][i] = 1; // Устанавливаем единицу на главной диагонали
            arrayTask13[i][size - 1 - i] = 1; // Устанавливаем единицу на побочной диагонали
        }

        // Печатаем массив
        printArrayTask13(arrayTask13);


        int len = 7; // Длина массива задание 14
        int initialValue = 1; // Начальное значение

        int[] arrayTask14 = createArray(len, initialValue); // Создаем массив

        // Печатаем массив
        printArrayTask14(arrayTask14);

    }




    public static void printThreeWords(){
        System.out.println("Orange\nBanana\nApple");
    }

    public static void checkSumSign() {
        int a = 5;  // Инициализация переменной a
        int b = -3; // Инициализация переменной b

        int sum = a + b; // Считаем сумму

        if (sum > 0) {
            System.out.println("Сумма положительная.");
        } else if (sum < 0) {
            System.out.println("Сумма отрицательная.");
        } else {
            System.out.println("Сумма равна нулю.");
        }
    }
    public static void printColor() {
        int value = 75; // Инициализация переменной value любым значением

        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    public static void compareNumbers() {
        int a = 10; // Инициализация переменной a
        int b = 5;  // Инициализация переменной b

        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }
    public static boolean isSumInRange(int a, int b) {
        int sum = a + b; // Вычисляем сумму

        // Проверяем, лежит ли сумма в диапазоне от 10 до 20 (включительно)
        return sum >= 10 && sum <= 20;
    }


    public static void checkNumber(int number) {
        if (number >= 0) {
            System.out.println(number + " - положительное число");
        } else {
            System.out.println(number + " - отрицательное число");
        }
    }

    public static boolean isNegative(int number) {
        return number < 0; // Возвращаем true, если число отрицательное
    }

    public static void printString(String text, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(text); // Печатаем строку
        }
    }

    public static boolean isLeapYear(int year) {
        // Проверяем условия для високосного года
        if (year % 400 == 0) {
            return true; // Каждый 400-й год является високосным
        } else if (year % 100 == 0) {
            return false; // Каждый 100-й год не является високосным
        } else if (year % 4 == 0) {
            return true; // Каждый 4-й год является високосным
        } else {
            return false; // Все остальные годы не являются високосными
        }


    }
    // Метод для печати массива
    public static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println(); // Переход на новую строку
    }


    public static void printArrayTask11(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println(); // Переход на новую строку
    }


    public static void printArrayTask12(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println(); // Переход на новую строку
    }
    public static void printArrayTask13(int[][] array) {
        for (int[] row : array) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println(); // Переход на новую строку
        }
    }
    // Метод для создания массива
    public static int[] createArray(int len, int initialValue) {
        int[] array = new int[len]; // Создаем массив заданной длины
        for (int i = 0; i < len; i++) {
            array[i] = initialValue; // Заполняем массив значением initialValue
        }
        return array; // Возвращаем заполненный массив
    }

    // Метод для печати одномерного массива
    public static void printArrayTask14(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println(); // Переход на новую строку
    }

}




