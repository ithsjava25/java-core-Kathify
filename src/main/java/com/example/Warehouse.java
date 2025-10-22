package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {

    private static final Map<String, Warehouse> instances = new HashMap<>();

    private final String name;
    private final List<Product> products = new ArrayList<>();

    private Warehouse(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Warehouse name cannot be null or blank.");
        this.name = name;
    }

    public static Warehouse getInstance(String name) {
        return instances.computeIfAbsent(name, Warehouse::new);
    }

    public String getName() {
        return name;
    }

    public void addProduct(Product product) {
        if (product == null)
            throw new IllegalArgumentException("Product cannot be null.");
        products.add(product);
    }

    public void remove(UUID id) {
        products.removeIf(p -> p.uuid().equals(id));
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> getProductById(UUID id) {
        return products.stream()
                .filter(p -> p.uuid().equals(id))
                .findFirst();
    }

    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("New price cannot be negative.");
        Product product = getProductById(id)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));


    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        return products.stream()
                .collect(Collectors.groupingBy(Product::category));
    }

    public List<Perishable> expiredProducts() {
        LocalDate today = LocalDate.now();
        return products.stream()
                .filter(p -> p instanceof Perishable)
                .map(p -> (Perishable) p)
                .filter(per -> per.expirationDate().isBefore(today))
                .collect(Collectors.toList());
    }

    public List<Shippable> shippableProducts() {
        return products.stream()
                .filter(p -> p instanceof Shippable)
                .map(p -> (Shippable) p)
                .collect(Collectors.toList());
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void clearProducts() {
        products.clear();
    }
}