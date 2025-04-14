package org.example;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class StudentManager {
    public static void removeLowAchievers(Set<Student> students) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getAverageGrade() < 3) {
                iterator.remove();
            }
        }
    }

    public static void promoteStudents(Set<Student> students) {
        for (Student student : students) {
            if (student.getAverageGrade() >= 3) {
                student.promote();
            }
        }
    }

    public static void printStudents(Set<Student> students, int course) {
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName());
            }
        }
    }

    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();
        students.add(new Student("Иванов И.И.", "Группа 1", 1, new double[]{4.0, 3.5, 5.0}));
        students.add(new Student("Петров П.П.", "Группа 1", 1, new double[]{2.0, 3.0, 2.5}));
        students.add(new Student("Сидоров С.С.", "Группа 2", 2, new double[]{4.5, 4.0, 5.0}));
        students.add(new Student("Сидоров Д.С.", "Группа 2", 2, new double[]{4.5, 3.0, 1.0}));

        System.out.println("Студенты на курсе 1:");
        printStudents(students, 1);

        System.out.println("\nУдаляем студентов со средним баллом < 3...");
        removeLowAchievers(students);

        System.out.println("\nСтуденты после удаления:");
        printStudents(students, 1);
        printStudents(students, 2); // Вывод студентов на курсе 2

        System.out.println("\nПереводим студентов на следующий курс...");
        promoteStudents(students);

        System.out.println("\nСтуденты после перевода:");
        printStudents(students, 2); // Вывод студентов на курсе 2
        printStudents(students, 3); // Теперь выведем студентов на курсе 3
    }
}
