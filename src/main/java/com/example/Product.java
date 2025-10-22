package com.example;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public abstract class Product {
    private final UUID id;
    private final String name;
    private final Category category;
    private BigDecimal price;

    protected Product(UUID id, String name, Category category, BigDecimal price){
        this.id = Objects.requireNonNull(id, "id can't be null");
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("name can't be null or blank");
        }
        this.name = Objects.requireNonNull(name, "name");
        this.category = Objects.requireNonNull(category, "category can't be null");
        this.price = Objects.requireNonNull(price, "price");
    }

    public BigDecimal price() {
        return price;
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

    public void price(BigDecimal newPrice) {
        this.price = Objects.requireNonNull(newPrice, "price");
    }


    public abstract String productDetails();


}