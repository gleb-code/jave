package org.example;

class Student {
    private String name;
    private String group;
    private int course;
    private double[] grades;

    public Student(String name, String group, int course, double[] grades) {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public int getCourse() {
        return course;
    }

    public double getAverageGrade() {
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.length;
    }

    public void promote() {
        course++;
    }

    @Override
    public String toString() {
        return name + " (Группа: " + group + ", Курс: " + course + ", Средний балл: " + getAverageGrade() + ")";
    }
}