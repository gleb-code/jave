package org.example;


public class product {
    private String name;
    private String dateProduction ;
    private String manufacturer;
    private String countryProduction;
    private double price;
    private String bookingStatus;

    // Конструктор класса
    public product(String name, String dateProduction, String manufacturer, String countryProduction, double price, String bookingStatus) {
        this.name = name;
        this.dateProduction = dateProduction;
        this.manufacturer = manufacturer;
        this.countryProduction = countryProduction;
        this.price = price;
        this.bookingStatus = bookingStatus;
    }

    // Метод для вывода информации о товаре
    public void displayInformation() {
        System.out.println("Название: " + name);
        System.out.println("Дата производства: " + dateProduction);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна происхождения: " + countryProduction);
        System.out.println("Цена: " + price + " руб.");
        System.out.println("Состояние бронирования: " + bookingStatus);
        System.out.println(); // Пустая строка для разделения товаров
    }

    // Пример использования класса
    public static void main(String[] args) {
        product[] productsArray = new product[5];
        productsArray[0] = new product ("Samsung","01.02.2025",
                "SamsungCorp","Korea",59990,"true");
        productsArray[1] = new product ("Geely","01.02.2024",
                "GeelyCorp","Belarus",69990,"false");
        productsArray[2] = new product ("Mersedes","01.02.2023",
                "MersedesCorp","Germany",79990,"true");
        productsArray[3] = new product ("Audi","01.02.2022",
                "AudiCorp","France",89990,"false");
        productsArray[4] = new product ("BMW","01.02.2021",
                "BMWCorp","England",99990,"false");


        for (product prod : productsArray) {
            prod.displayInformation();
        }



        product product1 = new product("Телевизор", "2023-01-15", "Samsung", "Южная Корея", 50000, "Забронирован");
        product1.displayInformation();

    }
}