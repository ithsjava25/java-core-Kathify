package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product implements Shippable {

    private final int warrantyMonths;
    private final BigDecimal weight;

    public ElectronicsProduct(UUID uuid, String name, Category category, BigDecimal price,
                              int warrantyMonths, BigDecimal weight) {
        super(uuid, name, category, price);
        if (warrantyMonths < 0)
            throw new IllegalArgumentException("Warranty months cannot be negative.");
        if (weight == null || weight.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Weight cannot be negative.");
        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }

    @Override
    public double weight() {
        return weight.doubleValue();
    }

    @Override
    public BigDecimal calculateShippingCost() {
        if (weight.doubleValue() > 5.0) {
            return BigDecimal.valueOf(79 + 49);
        }
        return BigDecimal.valueOf(79);
    }

    @Override
    public String productDetails() {
        return "Electronics: " + name() + ", Warranty: " + warrantyMonths + " months";
    }
}
