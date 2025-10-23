package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
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

    // hjälpmetoder
    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(products.get(id


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
