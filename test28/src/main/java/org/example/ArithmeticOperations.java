package org.example;

import java.util.Scanner;

public class ArithmeticOperations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод первого числа
        System.out.print("Введите первое целое число: ");
        int num1 = scanner.nextInt();

        // Ввод второго числа
        System.out.print("Введите второе целое число: ");
        int num2 = scanner.nextInt();

        // Ввод операции
        System.out.print("Выберите операцию (+, -, *, /): ");
        char operation = scanner.next().charAt(0);

        // Выполнение выбранной операции
        switch (operation) {
            case '+':
                System.out.println("Результат: " + add(num1, num2));
                break;
            case '-':
                System.out.println("Результат: " + subtract(num1, num2));
                break;
            case '*':
                System.out.println("Результат: " + multiply(num1, num2));
                break;
            case '/':
                if (num2 != 0) {
                    System.out.println("Результат: " + divide(num1, num2));
                } else {
                    System.out.println("Ошибка: Деление на ноль невозможно.");
                }
                break;
            default:
                System.out.println("Ошибка: Неверная операция.");
                break;
        }

        scanner.close();
    }

    // Метод для сложения
    public static int add(int a, int b) {
        return a + b;
    }

    // Метод для вычитания
    public static int subtract(int a, int b) {
        return a - b;
    }

    // Метод для умножения
    public static int multiply(int a, int b) {
        return a * b;
    }

    // Метод для деления
    public static double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Деление на ноль невозможно.");
        }
        return (double) a / b;
    }
}
