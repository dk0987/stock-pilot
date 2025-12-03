package com.sp.supplierservice.controller;

import com.sp.supplierservice.dto.SupplierRequestDTO;
import com.sp.supplierservice.dto.SupplierResponseDTO;
import com.sp.supplierservice.dto.SupplierResponseData;
import com.sp.supplierservice.service.SupplierService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @QueryMapping
    public SupplierResponseDTO<List<SupplierResponseData>> getAllSuppliers() {
        return supplierService.findAllSuppliers();
    }

    @QueryMapping
    public SupplierResponseDTO<SupplierResponseData> getSupplierById(@Argument("supplierId") UUID id) {
        return supplierService.findSupplierById(id);
    }

    @MutationMapping
    public SupplierResponseDTO<SupplierResponseData> createSuppliers(@Argument("input")SupplierRequestDTO request) {
        return supplierService.createSupplier(request);
    }

    @MutationMapping
    public SupplierResponseDTO<SupplierResponseData> updateSuppliers(@Argument("input")SupplierRequestDTO request , @Argument("supplierId")UUID id) {
        return supplierService.updateSupplier(request , id);
    }



}
