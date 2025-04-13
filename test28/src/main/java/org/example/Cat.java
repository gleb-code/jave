package org.example;
class Cat extends Animal {
    private static int catCount = 0; // Счетчик котов
    String name;
    private boolean isFull; // Поле сытости
    private int foodNeeded; // Количество еды, необходимое для сытости

    public Cat(String name) {
        super();
        this.name = name;
        this.isFull = false; // Кот изначально голоден
        this.foodNeeded = foodNeeded; // Устанавливаем количество еды для сытости
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
            // Проверяем, достаточно ли еды для сытости
            if (amount >= foodNeeded) {
                isFull = true; // Кот становится сытым
                System.out.println(name + " покушал " + amount + " единиц еды и стал сытым.");
            } else {
                System.out.println(name + " покушал " + amount + " единиц еды, но не стал сытым.");
            }
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