package com.sp.warehouseservice.dto;

import java.util.UUID;

public class StockDetailsResponseDTO {
    public UUID stockId ;
    public UUID productId ;
    public String productName ;
    public String productDescription ;
    public String productCategory ;
    public int productMaxOccupancy ;
    public int productQuantity ;

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public UUID getStockId() {
        return stockId;
    }

    public void setStockId(UUID stockId) {
        this.stockId = stockId;
    }

    public int getProductMaxOccupancy() {
        return productMaxOccupancy;
    }

    public void setProductMaxOccupancy(int productMaxOccupancy) {
        this.productMaxOccupancy = productMaxOccupancy;
    }
}
