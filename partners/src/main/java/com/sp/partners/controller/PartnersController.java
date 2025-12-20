package com.sp.partners.controller;

import com.sp.partners.dto.PartnersRequestDTO;
import com.sp.partners.dto.PartnersResponseDTO;
import com.sp.partners.services.PartnersServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/partners")
public class PartnersController {

    private final PartnersServices partnersServices;

    public PartnersController(PartnersServices partnersServices) {
        this.partnersServices = partnersServices;
    }

    @PostMapping("/purchase")
    public ResponseEntity<PartnersResponseDTO> createPartner(
            @Valid @RequestBody PartnersRequestDTO request,
            @RequestHeader("X-User-Id") Long performedBy
    ){
        PartnersResponseDTO response = partnersServices.createPartner(request,1L, performedBy);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

}
