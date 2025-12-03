package com.sp.productservice.util;

public enum Category {
    SPORTS(1),
    CASUALS(2),
    FORMALS(3),
    BOOTS(4),
    SLIPPERS(5),
    SANDALS(6);

    private final int value;

    Category(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
