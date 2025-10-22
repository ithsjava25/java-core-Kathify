package com.example;

import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDate;

public class FoodProduct extends Product implements Perishable {
    private LocalDate expirationDate;
    private BigDecimal weight;

    public FoodProduct(UUID id, String name, Category category, BigDecimal price, LocalDate expirationDate, BigDecimal weight) {
        super(id, name, category, price);  // Anropa konstruktorn för Product
        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    @Override
    public boolean isPerishable() {
        return true;  // Eftersom denna produkt är perishable
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;  // Returnera utgångsdatumet
    }

    public BigDecimal getWeight() {
        return weight;  // Returnera produktens vikt
    }

    @Override
    public boolean isShippable() {
        return true;  // Eftersom alla foodprodukter kan skickas (t.ex. livsmedel)
    }
}
