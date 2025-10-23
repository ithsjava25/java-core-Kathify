package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product implements Shippable {

    private final int warrantyMonths;
    private final BigDecimal weight;

    public ElectronicsProduct(UUID id, String name, Category category, BigDecimal price, int warrantyMonths, BigDecimal weight) {
        super(id, name, category, price);

        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("Warranty months cannot be negative.");
        }

        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }

    public int warrantyMonths() {
        return warrantyMonths;
    }

    @Override
    public BigDecimal calculateShippingCost() {

        BigDecimal cost = new BigDecimal("79");
        if (weight.compareTo(new BigDecimal("5")) > 0) {
            cost = cost.add(new BigDecimal("49"));
        }
        return cost;
    }

    @Override
    public double weight() {
        return weight.doubleValue();
    }

    @Override
    public String productDetails() {
        // produkt, garantimånad
        return String.format("Electronics: %s, Warranty: %d months", name(), warrantyMonths);
    }
}