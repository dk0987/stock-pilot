package com.sp.inventory.mapper;

import com.sp.inventory.dto.WarehouseRequestDTO;
import com.sp.inventory.model.StockThreshold;

public class StockThresholdMapper {

    public static StockThreshold toStockThreshold(WarehouseRequestDTO request , Long productId) {

        StockThreshold stockThreshold = new StockThreshold();
        stockThreshold.setWarehouseId(request.getWarehouseId());
        stockThreshold.setMaxQuantity(request.getMaxQuantity());
        stockThreshold.setMinQuantity(request.getMinQuantity());
        stockThreshold.setProductId(productId);
        return stockThreshold;

    }

}
