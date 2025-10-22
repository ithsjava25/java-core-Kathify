package com.example;

import java.util.UUID;
import java.math.BigDecimal;

public class ElectronicsProduct extends Product implements Shippable {
    private int warrantyPeriod;

    public ElectronicsProduct(UUID id, String name, Category category, BigDecimal price, int warrantyPeriod) {
        super(id, name, category, price);
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public boolean isPerishable() {
        return false;
    }

    @Override
    public boolean isShippable() {
        return true;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }
}
