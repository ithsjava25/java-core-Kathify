package com.example;

import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;


public class Warehouse {

    private static Warehouse instance;
    private final String name;
    private final List<Product> products = new ArrayList<>();

    private Warehouse(String name) {
        this.name = name;
    }

    public static Warehouse getInstance(String name) {
        if (instance == null) {
            instance = new Warehouse(name);
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void addProduct(Product product) {
        if (product != null) products.add(product);
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public Optional<Product> getProductById(UUID uuid) {
        return products.stream().filter(p -> p.uuid().equals(uuid)).findFirst();
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        return products.stream().collect(Collectors.groupingBy(Product::category));
    }

    public List<Perishable> expiredProducts() {
        LocalDate today = java.time.LocalDate.now();
        List<Perishable> result = new ArrayList<>();
        for (Product p : products) {
            if (p instanceof Perishable per) {
                if (per.expirationDate().isBefore(today)) {
                    result.add(per);
                }
            }
        }
        return result;
    }

    public List<Shippable> shippableProducts() {
        List<Shippable> result = new ArrayList<>();
        for (Product p : products) {
            if (p instanceof Shippable s) {
                result.add(s);
            }
        }
        return result;
    }
}
