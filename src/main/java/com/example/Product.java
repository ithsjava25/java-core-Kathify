package com.example;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public abstract class Product {

    private final UUID id;
    private final String name;
    private final Category category;
    private BigDecimal price;

    protected Product(UUID id, String name, Category category, BigDecimal price) {
        this.id = Objects.requireNonNull(id, "Product ID cannot be null");
        this.name = Objects.requireNonNull(name, "Product name cannot be null");
        this.category = Objects.requireNonNull(category, "Product category cannot be null");
        this.price = Objects.requireNonNull(price, "Product price cannot be null");
    }

    public UUID uuid() {
        return id;
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

    public void price(BigDecimal newPrice) {
        this.price = newPrice;
    }

    public abstract String productDetails();

    @Override
    public String toString() {
        return name + " (" + category.getName() + ")";
    }
}
