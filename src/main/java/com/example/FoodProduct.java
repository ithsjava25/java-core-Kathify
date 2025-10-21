package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FoodProduct extends Product implements Perishable, Shippable {

    public final LocalDate expirationDate;
    public final BigDecimal weight;

    public FoodProduct(UUID uuid, String name, Category category, BigDecimal price,
                       LocalDate expirationDate, BigDecimal weight) {
        super(uuid, name, category, price);

        if (expirationDate == null) throw new IllegalArgumentException("Expiration date cannot be null.");
        if (weight == null || weight.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Weight cannot be negative.");

        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    @Override
    public String productDetails() {
        return "Food: " + name() + ", Expires: " + expirationDate;
    }

    // perish
    @Override
    public LocalDate expirationDate() {
        return expirationDate;
    }

    // shippable
    @Override
    public Double weight() {
        return weight.doubleValue();
    }

    @Override
    public BigDecimal calculateShippingCost() {
        return weight.multiply(new BigDecimal("50")); // cost = weight * 50
    }
}
