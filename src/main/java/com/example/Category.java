package com.example;

import java.util.Objects;

public final class Category {
    private final String name;

    private Category(String name) {
        this.name = name;
    }

    public static Category of(String name) {
        return new Category(name);
    }

    public String getName() {
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
