package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class Product {

    private final UUID uuid;
    private final String name;
    private final Category category;
    private BigDecimal price;

    public Product(String name, Category category, BigDecimal price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null.");
        }
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null.");
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        this.uuid = UUID.randomUUID();
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public UUID uuid() {
        return uuid;
    }

    public String name() {
        return name;
    }

    public Category category() {
        return category;
    }

    public BigDecimal price() {
        return price;
    }

    // setter
    public void price(BigDecimal newPrice) {
        if (newPrice == null) {
            throw new IllegalArgumentException("Price cannot be null.");
        }
        if (newPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = newPrice;
    }

    public abstract String productDetails();
}
