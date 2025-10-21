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
        this.name = name;
    }

    public static Warehouse getInstance(String name) {
        return instances.computeIfAbsent(name, Warehouse::new);
    }

    public String getName() {
        return name;
    }

    public void addProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("Product cannot be null.");
        products.add(product);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(new ArrayList<>(products));
    }

    public Optional<Product> getProductById(UUID uuid) {
        return products.stream().filter(p -> p.uuid().equals(uuid)).findFirst();
    }

    public void updateProductPrice(UUID uuid, BigDecimal newPrice) {
        Product product = getProductById(uuid)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + uuid));
        if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Price cannot be negative.");
        // Assuming Product has a setter called setPrice
        product.setPrice(newPrice);
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        return products.stream().collect(Collectors.groupingBy(Product::category));
    }

    public List<Perishable> expiredProducts() {
        LocalDate today = LocalDate.now();
        List<Perishable> result = new ArrayList<>();
        for (Product p : products) {
            if (p instanceof Perishable per && per.isExpired()) {
                result.add(per);
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

    public void remove(UUID uuid) {
        products.removeIf(p -> p.uuid().equals(uuid));
    }

    public void clearProducts() {
        products.clear();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}
