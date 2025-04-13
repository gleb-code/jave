package org.example;

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
