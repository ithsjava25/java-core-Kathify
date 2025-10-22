package com.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name can't be blank");
        }

        String normalized = name.trim();
        normalized = normalized.substring(0, 1).toUpperCase() + normalized.substring(1);

        return INSTANCES.computeIfAbsent(normalized, Category::new);
    }

    // getter
    public String getName() {
        return name;
    }

    // jämförelse baserat på name
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Category)) return false;
        Category other = (Category) obj;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Category{" + "name='" + name + '\'' + '}';
    }
}