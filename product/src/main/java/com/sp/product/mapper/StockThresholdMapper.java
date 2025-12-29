package com.sp.product.mapper;

import com.example.productProto.ProductsEvents;
import com.sp.product.dto.StockThresholdRequestDTO;

public class StockThresholdMapper {

    public static ProductsEvents.StockThresholdKAF toStockThresholdEvent(
            StockThresholdRequestDTO request
    ){
        return ProductsEvents.StockThresholdKAF.newBuilder()
                .setWarehouseId(request.getWarehouseId())
                .setMaxQuantity(request.getMaxQuantity())
                .setMinQuantity(request.getMinQuantity())
                .build();

    }

}
