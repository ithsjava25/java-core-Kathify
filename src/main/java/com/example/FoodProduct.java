package com.example;

import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDate;

public class FoodProduct extends Product implements Perishable {
    private LocalDate expirationDate;
    private BigDecimal weight;

    public FoodProduct(UUID id, String name, Category category, BigDecimal price, LocalDate expirationDate, BigDecimal weight) {
        super(id, name, category, price);
        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    @Override
    public boolean isPerishable() {
        return true;
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    @Override
    public boolean isShippable() {
        return true;
    }
}
