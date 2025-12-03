package com.sp.supplierservice.service;

import com.sp.supplierservice.dto.SupplierRequestDTO;
import com.sp.supplierservice.dto.SupplierResponseDTO;
import com.sp.supplierservice.dto.SupplierResponseData;
import com.sp.supplierservice.mapper.SupplierMapper;
import com.sp.supplierservice.model.Supplier;
import com.sp.supplierservice.repository.SupplierRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public SupplierResponseDTO<SupplierResponseData> createSupplier(SupplierRequestDTO request) {
        SupplierResponseDTO<SupplierResponseData> response = new SupplierResponseDTO<>();
        try{
            if (supplierRepository.existsByName(request.getName())){
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.setMessage("Supplier with name " + request.getName() + " already exists" );
                return response;
            }
            Supplier supplier = SupplierMapper.toSupplier(request);
            supplier.setCreatedBy(UUID.fromString("a0e9b8c7-d6f5-e4d3-c2b1-100000000008"));

            Supplier savedSupplier = supplierRepository.save(supplier);

            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Supplier created successfully with id : " + savedSupplier.getId());
            response.setData(SupplierMapper.toSupplierResponse(savedSupplier));
            return response;

        } catch (RuntimeException e) {
            response.setMessage("Something went wrong or check all values.");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return response;
        }
    }

    public SupplierResponseDTO<SupplierResponseData> updateSupplier(SupplierRequestDTO request , UUID supplierId) {
        SupplierResponseDTO<SupplierResponseData> response = new SupplierResponseDTO<>();
        try{
            Supplier existingSupplier = supplierRepository.findById(supplierId).orElse(null);
            if (existingSupplier == null){
                response.setMessage("Supplier of id  " + supplierId + " does not exist");
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                return response;
            }
            String name = request.getName() == null ? existingSupplier.getName() : request.getName();
            String address = request.getAddress() == null ? existingSupplier.getAddress() : request.getAddress();
            String city = request.getCity() == null ? existingSupplier.getCity() : request.getCity();
            String state = request.getState() == null ? existingSupplier.getState() : request.getState();
            String phone = request.getPhone() == null ? existingSupplier.getPhone() : request.getPhone();
            String contactPerson = request.getContactPerson() == null ? existingSupplier.getContactPerson() : request.getContactPerson();
            String email = request.getEmail() == null ? existingSupplier.getEmail() : request.getEmail();
            String zip = request.getZip() == null ? existingSupplier.getZipcode() : request.getZip();
            String notes = request.getNotes() == null ? existingSupplier.getNotes() : request.getNotes();
            String country = request.getCountry() == null ? existingSupplier.getCountry() : request.getCountry();

            existingSupplier.setName(name);
            existingSupplier.setAddress(address);
            existingSupplier.setCity(city);
            existingSupplier.setState(state);
            existingSupplier.setPhone(phone);
            existingSupplier.setContactPerson(contactPerson);
            existingSupplier.setEmail(email);
            existingSupplier.setZipcode(zip);
            existingSupplier.setNotes(notes);
            existingSupplier.setCountry(country);
            existingSupplier.setUpdatedBy(UUID.fromString("a0e9b8c7-d6f5-e4d3-c2b1-100000000008"));
            existingSupplier.setUpdatedAt(LocalDateTime.now());

            Supplier updatedSupplier = supplierRepository.save(existingSupplier);

            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Supplier updated successfully with id : " + updatedSupplier.getId());
            response.setData(SupplierMapper.toSupplierResponse(updatedSupplier));
            return response;

        }catch (RuntimeException e) {
            response.setMessage("Something went wrong or check all values.");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return response;
        }
    }

    public SupplierResponseDTO<List<SupplierResponseData>> findAllSuppliers() {
        SupplierResponseDTO<List<SupplierResponseData>> response = new SupplierResponseDTO<>();
        try{
            List<SupplierResponseData> suppliers = supplierRepository.findAll()
                    .stream().map(SupplierMapper::toSupplierResponse).toList();

            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Retrieved All suppliers successfully");
            response.setData(suppliers);
            return response;

        } catch (RuntimeException e) {
            response.setMessage("Something went wrong or check all values.");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return response;
        }
    }

    public SupplierResponseDTO<SupplierResponseData> findSupplierById(UUID supplierId) {
        SupplierResponseDTO<SupplierResponseData> response = new SupplierResponseDTO<>();
        try {
            Supplier supplier = supplierRepository.findById(supplierId).orElse(null);
            if (supplier == null){
                response.setMessage("Supplier of id  " + supplierId + " does not exist");
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                return response;
            }
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Supplier found with id : " + supplier.getId());
            response.setData(SupplierMapper.toSupplierResponse(supplier));
            return response;
        } catch (RuntimeException e) {
            response.setMessage("Something went wrong or check all values.");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return response;
        }
    }


}
