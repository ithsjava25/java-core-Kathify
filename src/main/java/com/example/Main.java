package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        // Skapa lager
        Warehouse warehouse = Warehouse.getInstance("Huvudlager");

        // Skapa några kategorier
        Category dairy = Category.of("Dairy");
        Category electronics = Category.of("Electronics");

        // Lägg till produkter
        warehouse.addProduct(new FoodProduct(UUID.randomUUID(), "Milk", dairy, new BigDecimal("20.00"), LocalDate.now().plusDays(5), new BigDecimal("1.0")));
        warehouse.addProduct(new ElectronicsProduct(UUID.randomUUID(), "Mobiltelefon", electronics, new BigDecimal("5999.00"), 24));

        // Analysera lagret
        WarehouseAnalyzer analyzer = new WarehouseAnalyzer(warehouse);

        // Hitta produkter som går ut om 5 dagar
        List<FoodProduct> expiringSoon = analyzer.findProductsExpiringWithinDays(5);

        // Skriv ut resultat
        if (expiringSoon.isEmpty()) {
            System.out.println("Inga produkter går ut inom 5 dagar.");
        } else {
            System.out.println("Produkter som går ut inom 5 dagar:");
            for (FoodProduct product : expiringSoon) {
                System.out.println(product.getName() + " går ut den " + product.getExpirationDate());
            }
        }
    }
}
