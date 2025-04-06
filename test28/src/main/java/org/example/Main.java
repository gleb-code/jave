package org.example;

public class Main {
    public static void main(String[] args) {
        Bowl bowl = new Bowl(20); // Создаем миску с 20 единицами еды

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



class Animal {
    private static int totalCount = 0; // Счетчик всех животных

    public Animal() {
        totalCount++;
    }

    public static int getTotalCount() {
        return totalCount;
    }

    public void run(int distance) {
        throw new UnsupportedOperationException();
    }

    public void swim(int distance) {
        throw new UnsupportedOperationException();
    }
}

class Dog extends Animal {
    private static int dogCount = 0; // Счетчик собак
    private String name;

    public Dog(String name) {
        super();
        this.name = name;
        dogCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= 500 ) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м.");
        }
    }



    @Override
    public void swim(int distance) {
        if (distance <= 10) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не может проплыть " + distance + " м.");
        }
    }

    public static int getDogCount() {
        return dogCount;
    }
}

class Cat extends Animal {
    private static int catCount = 0; // Счетчик котов
    String name;
    private boolean isFull; // Поле сытости

    public Cat(String name) {
        super();
        this.name = name;
        this.isFull = false; // Кот изначально голоден
        catCount++;
    }

    @Override
    public void run(int distance) {
        if (!isFull) {
            System.out.println(name + " голоден и не может бегать.");
            return;
        }
        if (distance <= 200) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать.");
    }

    public void eat(Bowl bowl, int amount) {

        if (isFull) {
            System.out.println(name + " уже сыт и не хочет есть.");
            return;
        }
        if (bowl.getFoodAmount() >= amount) {
            bowl.decreaseFood(amount);
            isFull = true; // Кот становится сытым
            System.out.println(name + " покушал " + amount + " еды.");
        } else {
            System.out.println(name + " не может покушать, недостаточно еды в миске.");
        }
    }

    public boolean isFull() {
        return isFull;
    }

    public static int getCatCount() {
        return catCount;
    }
}



class Bowl {
    private int foodAmount;

    public Bowl(int initialFood) {
        if (initialFood < 0) {
            this.foodAmount = 0; // Не допускаем отрицательного количества еды
        } else {
            this.foodAmount = initialFood;
        }
    }

    public void addFood(int amount) {
        if (amount > 0) {
            foodAmount += amount;
            System.out.println("В миску добавлено " + amount + " еды. Теперь в миске " + foodAmount + " еды.");
        }
    }

    public void decreaseFood(int amount) {
        if (amount <= foodAmount) {
            foodAmount -= amount;
        }
    }

    public int getFoodAmount() {
        return foodAmount;
    }
}

