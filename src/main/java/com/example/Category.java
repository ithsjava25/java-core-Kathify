package com.example;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class Category {

    private static final Map<String, Category> CACHE = new ConcurrentHashMap<>();

    private final String name;

    private Category(String normalizedName) {
        this.name = normalizedName;
    }

    public static Category of(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name cannot be blank");
        }

        String normalized = normalize(name);
        return CACHE.computeIfAbsent(normalized, Category::new);
    }

    private static String normalize(String input) {
        input = input.trim();

        String spaced = input.replaceAll("([A-Z])", " $1").toLowerCase().trim();

        String[] parts = spaced.split("[^a-z]+");
        StringBuilder sb = new StringBuilder();

        for (String part : parts) {
            if (part.isEmpty()) continue;
            sb.append(Character.toUpperCase(part.charAt(0)));
            if (part.length() > 1) {
                sb.append(part.substring(1));
            }
        }

        return sb.toString();
    }

    // getters
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
