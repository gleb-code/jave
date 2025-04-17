package org.example;

import java.util.Scanner;



public class CompareIntegers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод первого числа
        System.out.print("Введите первое целое число: ");
        int num1 = scanner.nextInt();

        // Ввод второго числа
        System.out.print("Введите второе целое число: ");
        int num2 = scanner.nextInt();

        // Сравнение чисел
        String result = compare(num1, num2);
        System.out.println(result);

        scanner.close();
    }

    // Метод для сравнения двух целых чисел
    public static String compare(int a, int b) {
        if (a > b) {
            return a + " больше, чем " + b;
        } else if (a < b) {
            return a + " меньше, чем " + b;
        } else {
            return a + " равно " + b;
        }
    }
}
