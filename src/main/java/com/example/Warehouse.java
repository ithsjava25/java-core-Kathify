package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Warehouse {

    private static final Map<String, Warehouse> instances = new ConcurrentHashMap<>();

    public static Warehouse getInstance(String name) {
        return instances.computeIfAbsent(name, Warehouse::new);
    }

    private final String name;
    private final Map<UUID, Product> products = new HashMap<>();
    private final List<Product> changedProducts = new ArrayList<>();

    private Warehouse(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Warehouse name cannot be blank");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addProduct(Product product) {
        Objects.requireNonNull(product, "Product cannot be null");
        if (products.containsKey(product.getId())) {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }
        products.put(product.getId(), product);
    }

    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        Objects.requireNonNull(id, "Id cannot be null");
        Objects.requireNonNull(newPrice, "Price cannot be null");

        Product existing = products.get(id);
        if (existing == null) {
            throw new NoSuchElementException("Product with that id does not exist.");
        }

        existing.setPrice(newPrice);
        changedProducts.add(existing);
    }

    public Product remove(UUID id) {
        Objects.requireNonNull(id, "Id cannot be null");
        return products.remove(id);
    }

    public List<Product> getProducts() {
        return List.copyOf(products.values());
    }

    public List<Product> getChangedProducts() {
        return List.copyOf(changedProducts);
    }

    public void clearChangedProducts() {
        changedProducts.clear();
    }

    // Hjälpmetoder
    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        return products.values().stream()
                .collect(Collectors.groupingBy(Product::category));
    }

    public List<Perishable> expiredProducts() {
        LocalDate today = LocalDate.now();
        return products.values().stream()
                .filter(p -> p instanceof Perishable)
                .map(p -> (Perishable) p)
                .filter(per -> per.expirationDate().isBefore(today))
                .collect(Collectors.toList());
    }

    public List<Shippable> shippableProducts() {
        return products.values().stream()
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
