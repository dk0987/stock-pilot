package com.sp.inventory.mapper;

import com.sp.inventory.dto.StockThresholdRequestDTO;
import com.sp.inventory.model.StockThreshold;
import com.sp.inventoryProto.ProductsEvents;

public class StockThresholdMapper {

    public static StockThreshold toStockThreshold(StockThresholdRequestDTO request , Long productId) {

        StockThreshold stockThreshold = new StockThreshold();
        stockThreshold.setWarehouseId(request.getWarehouseId());
        stockThreshold.setMaxQuantity(request.getMaxQuantity());
        stockThreshold.setMinQuantity(request.getMinQuantity());
        stockThreshold.setProductId(productId);
        return stockThreshold;

    }

    public static StockThresholdRequestDTO toStockThresholdRequest(
            ProductsEvents.StockThresholdKAF event
    ) {
       StockThresholdRequestDTO stockThresholdRequestDTO = new StockThresholdRequestDTO();
       stockThresholdRequestDTO.setWarehouseId(event.getWarehouseId());
       stockThresholdRequestDTO.setMaxQuantity(event.getMaxQuantity());
       stockThresholdRequestDTO.setMinQuantity(event.getMinQuantity());
       return stockThresholdRequestDTO;
    }

}
