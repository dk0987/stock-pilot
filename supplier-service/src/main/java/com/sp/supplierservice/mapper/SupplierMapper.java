package com.sp.supplierservice.mapper;

import com.sp.supplierservice.dto.SupplierRequestDTO;
import com.sp.supplierservice.dto.SupplierResponseDTO;
import com.sp.supplierservice.dto.SupplierResponseData;
import com.sp.supplierservice.model.Supplier;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class SupplierMapper {

    public static Supplier toSupplier(SupplierRequestDTO request){
        Supplier supplier = new Supplier();
        supplier.setName(request.getName());
        supplier.setAddress(request.getAddress());
        supplier.setCity(request.getCity());
        supplier.setCountry(request.getCountry());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        supplier.setContactPerson(request.getContactPerson());
        supplier.setState(request.getState());
        supplier.setZipcode(request.getZip());
        supplier.setNotes(request.getNotes());
        supplier.setActive(true);
        supplier.setCreatedAt(LocalDateTime.now());
        return supplier;
    }

    public static SupplierResponseData toSupplierResponse(Supplier supplier){
        SupplierResponseData supplierResponseData = new SupplierResponseData();
        supplierResponseData.setId(supplier.getId());
        supplierResponseData.setAddress(supplier.getAddress());
        supplierResponseData.setName(supplier.getName());
        supplierResponseData.setCity(supplier.getCity());
        supplierResponseData.setCountry(supplier.getCountry());
        supplierResponseData.setPhone(supplier.getPhone());
        supplierResponseData.setEmail(supplier.getEmail());
        supplierResponseData.setContactPerson(supplier.getContactPerson());
        supplierResponseData.setState(supplier.getState());
        supplierResponseData.setZip(supplier.getZipcode());
        supplierResponseData.setNotes(supplier.getNotes());
        supplierResponseData.setCreatedAt(supplier.getCreatedAt().atOffset(ZoneOffset.UTC));
        supplierResponseData.setCreatedBy(supplier.getCreatedBy());
        supplierResponseData.setUpdatedAt(supplier.getUpdatedAt().atOffset(ZoneOffset.UTC));
        return supplierResponseData;
    }
}
