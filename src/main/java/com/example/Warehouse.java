package com.example;

import java.util.*;
import java.util.stream.Collectors;
import java.math.BigDecimal;


public class Warehouse {

    private static final Map<String, Warehouse> instances = new HashMap<>();
    private final List<Product> products = new ArrayList<>();

    private Warehouse(String name) {
    }

    public static Warehouse getInstance(String name) {
        return instances.computeIfAbsent(name, Warehouse::new);
    }

    public void addProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null.");
        products.add(product);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> getProductById(UUID uuid) {
        return products.stream().filter(p -> p.uuid().equals(uuid)).findFirst();
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        return products.stream().collect(Collectors.groupingBy(Product::category));
    }

    public List<Perishable> expiredProducts() {
        return products.stream()
                .filter(p -> p instanceof Perishable)
                .map(p -> (Perishable)p)
                .filter(Perishable::isExpired)
                .toList();
    }

    public List<Shippable> shippableProducts() {
        return products.stream()
                .filter(p -> p instanceof Shippable)
                .map(p -> (Shippable)p)
                .toList();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void clearProducts() {
        products.clear();
    }

    public void remove(UUID uuid) {
        products.removeIf(p -> p.uuid().equals(uuid));
    }

    public void updateProductPrice(UUID uuid, BigDecimal newPrice) {
        Product p = getProductById(uuid).orElseThrow(() ->
                new NoSuchElementException("Product not found with id: " + uuid));
        p.price(newPrice);
    }
}