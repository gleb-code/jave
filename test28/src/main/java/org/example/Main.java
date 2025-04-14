package org.example;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        // Добавляем записи
        phoneBook.add("Иванов", "123-45-67");
        phoneBook.add("Иванов", "987-65-43");
        phoneBook.add("Петров", "234-56-78");
        phoneBook.add("Сидоров", "345-67-89");
        phoneBook.add("Артемов", "325-61-86");
        phoneBook.add("Егоров", "235-57-79");
        phoneBook.add("Сидоров", "149-27-39");

        // Получаем номера по фамилии
        System.out.println("Номера для Иванов: " + phoneBook.get("Иванов"));
        System.out.println("Номера для Петров: " + phoneBook.get("Петров"));
        System.out.println("Номера для Сидоров: " + phoneBook.get("Сидоров"));
        System.out.println("Номера для Артемов: " + phoneBook.get("Артемов"));
        System.out.println("Номера для Егоров: " + phoneBook.get("Егоров"));
        System.out.println("Номера для Смирнов: " + phoneBook.get("Смирнов")); // Фамилия не найдена

        // Отображаем весь справочник
        System.out.println("\nСодержимое телефонного справочника:");
        phoneBook.display();
    }
}