package com.example;

import java.math.BigDecimal;

public interface Shippable {
    BigDecimal weight();

    default double getWeightAsDouble() {
        return weight() != null ? weight().doubleValue() : 0.0;
    }

    // beräkna fraktkostnad
    BigDecimal calculateShippingCost();
}
