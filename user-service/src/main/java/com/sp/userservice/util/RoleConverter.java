package com.sp.userservice.util;

import com.sp.userservice.model.Roles;

import java.util.Objects;
// Assume Role is the refactored enum from Step 1

public class RoleConverter {

    public static int getRoleCode(Roles role_name) {
        // Ensure the role entity and its name are not null
        if (role_name == null || role_name.getName() == null) {
            // Return a default value or throw a specific exception
            return Role.VIEWER.getCode(); // Default to 5
        }

        try {
            // 1. Convert the database string name (e.g., "ADMIN") to the Enum constant (Role.ADMIN)
            Role roleConstant = Role.valueOf(role_name.getName().toUpperCase());

            // 2. Return the integer code stored in the enum
            return roleConstant.getCode();

        } catch (IllegalArgumentException e) {
            // Handle cases where the DB string doesn't match any enum constant
            System.err.println("Unrecognized role name found in database: " + role_name.getName());
            return Role.VIEWER.getCode(); // Default to the viewer code (5)
        }
    }

    public static String getRoleName(int code) {
        return switch (code) {
            case 1 -> Role.ADMIN.name();
            case 2 -> Role.INVENTORY_MANAGER.name();
            case 3 -> Role.SALES_MANAGER.name();
            case 4 -> Role.APPROVER.name();
            default -> Role.VIEWER.name();
        };
    }
}