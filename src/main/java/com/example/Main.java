package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        Warehouse warehouse = Warehouse.getInstance("MyWarehouse");

        // Skapa några produkter
        FoodProduct milk = new FoodProduct(
                UUID.randomUUID(),
                "Milk",
                Category.of("Dairy"),
                new BigDecimal("15.50"),
                LocalDate.now().plusDays(5), // färsk
                new BigDecimal("1.0")
        );

        FoodProduct oldMilk = new FoodProduct(
                UUID.randomUUID(),
                "Old Milk",
                Category.of("Dairy"),
                new BigDecimal("10.00"),
                LocalDate.now().minusDays(1), // utgången
                new BigDecimal("1.0")
        );

        ElectronicsProduct laptop = new ElectronicsProduct(
                UUID.randomUUID(),
                "Laptop",
                Category.of("Electronics"),
                new BigDecimal("12345.0"),
                24,
                new BigDecimal("2.2")
        );

        warehouse.addProduct(milk);
        warehouse.addProduct(oldMilk);
        warehouse.addProduct(laptop);

        // visa produkter
        System.out.println("Products in warehouse:");
        warehouse.getProducts().forEach(p -> System.out.println(p.productDetails()));

        // hämta produkt via UUID
        UUID milkId = milk.uuid();
        System.out.println("\nGet product by ID:");
        warehouse.getProductById(milkId)
                .ifPresent(p -> System.out.println(p.productDetails()));

        System.out.println("\nProducts grouped by category:");
        warehouse.getProductsGroupedByCategories()
                .forEach((cat, list) -> System.out.println(cat.getName() + ": " + list.size() + " products"));

        // skriver ut hur det ser ut med utgångna produkter
        System.out.println("\nExpired products:");
        warehouse.expiredProducts().forEach(perishable -> {
            Product p = (Product) perishable;
            System.out.println(p.name() + " (expires: " + ((Perishable) p).expirationDate() + ")");
        });

        // lista shippable
        System.out.println("\nShippable products:");
        warehouse.shippableProducts().forEach(shippable -> {
            Product p = (Product) shippable;
            System.out.println(p.name() + " (weight: " + ((Shippable) p).weight() + " kg, shipping cost: " + ((Shippable) p).calculateShippingCost() + ")");
        });
    }
}
