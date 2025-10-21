package com.example;

import java.util.Objects;

public class Category {
    private final String name;

    private Category(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Category name cannot be null or blank.");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Category of(String name) {
        return new Category(name);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return name.equalsIgnoreCase(category.name);
    }

    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    public String toString() {
        return name;
    }
}
