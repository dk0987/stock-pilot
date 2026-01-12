package com.sp.inventory.mapper;

import com.sp.inventory.dto.SupplierProductRequestDTO;
import com.sp.inventory.model.SupplierProduct;
import com.sp.inventoryProto.ProductsEvents;

public class SupplierProductMapper {

    public static SupplierProduct toSupplierProduct(SupplierProductRequestDTO request , Long productId) {

        SupplierProduct supplierProduct = new SupplierProduct();
        supplierProduct.setSupplierId(request.getSupplierId());
        supplierProduct.setProductId(productId);
        supplierProduct.setTransactionCurrency(request.getTransactionCurrency());

        return supplierProduct;

    }

    public static SupplierProductRequestDTO toSupplierProductRequest(
            ProductsEvents.SupplierProductEventKAF supplierProduct
    ) {

        SupplierProductRequestDTO supplierProductRequestDTO = new SupplierProductRequestDTO();
        supplierProductRequestDTO.setSupplierId(supplierProduct.getSupplierId());
        supplierProductRequestDTO.setTransactionCurrency(supplierProduct.getTransactionalCurrency());
        return supplierProductRequestDTO;

    }

}
