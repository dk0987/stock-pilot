package com.sp.userservice.util;

public enum Role {
    ADMIN(1), // Store the integer code directly
    INVENTORY_MANAGER(2),
    SALES_MANAGER(3),
    APPROVER(4),
    VIEWER(5); // Assuming 5 is the default 'else' value

    private final int code;

    // Constructor to assign the code
    Role(int code) {
        this.code = code;
    }

    // Public method to retrieve the code
    public int getCode() {
        return code;
    }
}