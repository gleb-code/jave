package org.example;
import java.util.*;


public class Main {
    public static void main(String[] args) {

        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
        System.out.println(isSumInRange(50, -30));  // Пример вызова метода
        System.out.println(isSumInRange(100, 91)); // Пример вызова метода
        System.out.println(isSumInRange(3, -8));  // Пример вызова метода

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
}




