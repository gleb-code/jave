package org.example;

public class Main {
    public static void main(String[] args) {
        Bowl bowl = new Bowl(15); // Создаем миску с 20 единицами еды

        Cat[] cats = {
                new Cat("Мурзик"),
                new Cat("Барсик"),
                new Cat("Снежок")
        };

        Dog dog = new Dog("Шарик");

        // Коты пытаются покушать из миски
        for (Cat cat : cats) {
            cat.eat(bowl, 10); // Каждый кот пытается покушать 10 еды
            cat.run(150); // Каждый кот пробегает 150 метров
            cat.swim(1);
        }

        // Вывод информации о сытости котов
        for (Cat cat : cats) {
            System.out.println(cat.name + " сытость: " + (cat.isFull() ? "Сыт" : "Голоден"));
        }

        // Добавляем еду в миску
        bowl.addFood(15); // Добавляем 15 единиц еды

        // Коты пытаются покушать снова
        for (Cat cat : cats) {
            cat.eat(bowl, 10); // Каждый кот снова пытается покушать 10 еды
            cat.run(150); // Каждый кот пробегает 150 метров
            cat.swim(2);
        }

        // Вывод информации о сытости котов
        for (Cat cat : cats) {
            System.out.println(cat.name + " сытость: " + (cat.isFull() ? "Сыт" : "Голоден"));
        }

        // Собака пробегает расстояние
        dog.run(300); // Собака пробегает 300 метров
        dog.swim(5); // Собака плывет 5 метров

        // Вывод общего количества животных
        System.out.println("Всего животных: " + Animal.getTotalCount());
        System.out.println("Всего собак: " + Dog.getDogCount());
        System.out.println("Всего котов: " + Cat.getCatCount());
    }
}












