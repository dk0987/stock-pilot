package com.sp.partners.util;

public class CommonMethods {
    
    public static String getPartnerType(Long partnerTypeId){
        return switch (partnerTypeId.intValue()) {
            case 1 -> "Supplier";
            case 2 -> "Customer";
            default -> throw new IllegalStateException("Unexpected value: " + partnerTypeId.intValue());
        };
    }

}
