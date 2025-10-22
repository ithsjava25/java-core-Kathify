package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product implements Shippable {

    private final int warrantyMonths;
    private final BigDecimal weight;

    public ElectronicsProduct(UUID uuid, String name, Category category, BigDecimal price,
                              int warrantyMonths, BigDecimal weight) {
        super(uuid, name, category, price);

        if (weight == null || weight.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Weight cannot be negative.");
        if (warrantyMonths < 0)
            throw new IllegalArgumentException("Warranty months cannot be negative.");

        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }

    @Override
    public String productDetails() {
        return "Electronics: " + name() + ", Warranty: " + warrantyMonths + " months, Price: " + price();
    }

    // shipp
    @Override
    public BigDecimal weight() {
        return weight;
    }

    @Override
    public BigDecimal calculateShippingCost() {

         //weight större än 5.0kg lägg till 49
        BigDecimal baseCost = BigDecimal.valueOf(79);
        if (weight.compareTo(BigDecimal.valueOf(5.0)) > 0) {
            baseCost = baseCost.add(BigDecimal.valueOf(49));
        }
        return baseCost;
    }

    public int warrantyMonths() {
        return warrantyMonths;
    }
}
