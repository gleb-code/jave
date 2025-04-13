package org.example;

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