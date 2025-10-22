package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Warehouse {
    private static Warehouse instance;
    private String name;
    private List<Product> products;

    // Singleton pattern
    private Warehouse(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    public static Warehouse getInstance(String name) {
        if (instance == null) {
            instance = new Warehouse(name);
        }
        return instance;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }

    // Ny metod som returnerar endast shippable produkter
    public List<Shippable> shippableProducts() {
        // Filtrerar ut alla produkter som implementerar Shippable
        return products.stream()
                .filter(p -> p instanceof Shippable)  // Filtrera för att få endast Shippable produkter
                .map(p -> (Shippable) p)  // Omvandla till Shippable
                .collect(Collectors.toList());  // Samla i en lista
    }
}
