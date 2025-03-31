package org.example;

import java.util.ArrayList;
import java.util.List;

public class Park {
    private String name;
    private List<Attraction> attractions;

    // Конструктор класса Парк
    public Park(String name) {
        this.name = name;
        this.attractions = new ArrayList<>();
    }

    // Внутренний класс Аттракцион
    public class Attraction {
        private String name;
        private String timeWork;
        private double price;

        // Конструктор класса Аттракцион
        public Attraction(String name, String timeWork, double price) {
            this.name = name;
            this.timeWork = timeWork;
            this.price = price;
        }

        // Метод для вывода информации об аттракционе
        public void displayInformation() {
            System.out.println("Аттракцион: " + name);
            System.out.println("Время работы: " + timeWork);
            System.out.println("Стоимость: " + price + " руб.");
            System.out.println(); // Пустая строка для разделения аттракционов
        }
    }

    // Метод для добавления аттракциона в парк
    public void addAttraction(String name, String timeWork, double price) {
        Attraction attraction = new Attraction(name, timeWork, price);
        attractions.add(attraction);
    }

    // Метод для вывода информации обо всех аттракционах в парке
    public void displayAllAttractions() {
        System.out.println("Аттракционы в парке " + name + ":");
        for (Attraction attraction : attractions) {
            attraction.displayInformation();
        }
    }

    // Пример использования класса
    public static void main(String[] args) {
        Park park = new Park("Сказочный парк");

        // Добавляем аттракционы
        park.addAttraction("Американские горки", "10:00 - 22:00", 500);
        park.addAttraction("Колесо обозрения", "09:00 - 21:00", 300);
        park.addAttraction("Водные горки", "10:00 - 20:00", 400);

        // Выводим информацию обо всех аттракционах
        park.displayAllAttractions();
    }
}

