package com.sp.product.mapper;

import com.example.productProto.ProductsEvents;
import com.sp.product.dto.SupplierProductRequestDTO;

public class SupplierProductMapper {

    public static ProductsEvents.SupplierProductEventKAF toSupplierEvent(
            SupplierProductRequestDTO request
    ){
        return ProductsEvents.SupplierProductEventKAF.newBuilder()
                .setSupplierId(request.getSupplierId())
                .setTransactionalCurrency(request.getTransactionCurrency())
                .build();
    }



}
