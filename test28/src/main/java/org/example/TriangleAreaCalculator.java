package org.example;

import java.util.Scanner;

public class TriangleAreaCalculator {

    public static double calculateArea(double a, double b, double c) {
        // Проверка на существование треугольника
        if (a <= 0 || b <= 0 || c <= 0 || a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Стороны не могут образовать треугольник.");
        }

        double s = (a + b + c) / 2; // Полупериметр
        return Math.sqrt(s * (s - a) * (s - b) * (s - c)); // Формула Герона
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите длину первой стороны треугольника: ");
        double a = scanner.nextDouble();

        System.out.print("Введите длину второй стороны треугольника: ");
        double b = scanner.nextDouble();

        System.out.print("Введите длину третьей стороны треугольника: ");
        double c = scanner.nextDouble();

        try {
            double area = calculateArea(a, b, c);
            System.out.println("Площадь треугольника равна: " + area);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
