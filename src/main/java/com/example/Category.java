package com.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Objects;

public final class Category {
    private static final Map<String, Category> INSTANCES = new ConcurrentHashMap<>();
    private final String name;

    private Category(String name) {
        this.name = name;
    }

    public static Category of(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }

        String trimmed = name.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Category name can't be blank");
        }

        String formatted = trimmed.substring(0, 1).toUpperCase() + trimmed.substring(1);

        return INSTANCES.computeIfAbsent(formatted, Category::new);
    }

    // Statisk konstant för Electronics
    public static final Category ELECTRONICS = Category.of("Electronics");

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}