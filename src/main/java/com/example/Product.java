package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class Product {
    private UUID id;
    private String name;
    private Category category;
    private BigDecimal price;

    // Price method (använd den istället för getPrice)
    public BigDecimal price() {
        return this.price;
    }

    public Product(UUID id, String name, Category category, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Category category() {
        return category;
    }

    public UUID getId() {
        return id;
    }

    // Abstrakt metod för att avgöra om produkten är perishable (för livsmedel)
    public abstract boolean isPerishable();
}
