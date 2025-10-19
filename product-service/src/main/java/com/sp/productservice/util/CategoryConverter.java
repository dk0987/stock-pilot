package com.sp.productservice.util;

import com.sp.productservice.model.Category;

public class CategoryConverter {

    public static int getCategoryCode(Category category) {
        // Ensure the role entity and its name are not null
        if (category == null || category.getName() == null) {
            // Return a default value or throw a specific exception
            return 0;
        }

        try {
            // 1. Convert the database string name (e.g., "ADMIN") to the Enum constant (Role.ADMIN)
            com.sp.productservice.util.Category categoryConstant = com.sp.productservice.util.Category.valueOf(category.getName().toUpperCase());
            // 2. Return the integer code stored in the enum
            return categoryConstant.getValue();

        } catch (IllegalArgumentException e) {
            // Handle cases where the DB string doesn't match any enum constant
            System.err.println("Unrecognized category found in database: " + category.getName());
            return 0;
        }
    }

    public static String getCategoryName(int value) {
        return switch (value) {
            case 1 -> com.sp.productservice.util.Category.SPORTS.name();
            case 2 -> com.sp.productservice.util.Category.CASUALS.name();
            case 3 -> com.sp.productservice.util.Category.FORMALS.name();
            case 4 -> com.sp.productservice.util.Category.BOOTS.name();
            case 5 -> com.sp.productservice.util.Category.SLIPPERS.name();
            case 6 -> com.sp.productservice.util.Category.SANDALS.name();
            default -> null;
        };
    }
}
