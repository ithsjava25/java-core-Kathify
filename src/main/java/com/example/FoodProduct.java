package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class FoodProduct extends Product implements Perishable, Shippable {

    private final LocalDate expirationDate;
    private final BigDecimal weight;

    public FoodProduct(UUID uuid, String name, Category category, BigDecimal price,
                       LocalDate expirationDate, BigDecimal weight) {
        super(uuid, name, category, price);

        if (expirationDate == null)
            throw new IllegalArgumentException("Expiration date cannot be null.");
        if (weight == null || weight.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Weight cannot be negative.");

        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    @Override
    public String productDetails() {
        return "Food: " + name() + ", Expires: " + expirationDate + ", Price: " + getDiscountedPrice();
    }

    // perish
    @Override
    public LocalDate expirationDate() {
        return expirationDate;
    }

    // shipp
    @Override
    public BigDecimal weight() {
        return weight;
    }

    @Override
    public BigDecimal calculateShippingCost() {
        // exempel 50kg
        return weight.multiply(BigDecimal.valueOf(50));
    }

    public BigDecimal getDiscountedPrice() {
        LocalDate today = LocalDate.now();
        long daysToExpire = ChronoUnit.DAYS.between(today, expirationDate);

        BigDecimal originalPrice = price();

        if (daysToExpire < 0) {
            // om det utgått är priset 0
            return BigDecimal.ZERO;
        } else if (daysToExpire == 0) {
            return originalPrice.multiply(BigDecimal.valueOf(0.50));
        } else if (daysToExpire == 1) {
            return originalPrice.multiply(BigDecimal.valueOf(0.70));
        } else if (daysToExpire <= 3) {
            return originalPrice.multiply(BigDecimal.valueOf(0.85));
        } else {
            return originalPrice;
        }
    }
}
