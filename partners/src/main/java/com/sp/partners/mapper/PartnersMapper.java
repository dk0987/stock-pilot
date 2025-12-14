package com.sp.partners.mapper;

import com.sp.partners.dto.AddressResponseDTO;
import com.sp.partners.dto.PartnersRequestDTO;
import com.sp.partners.dto.PartnersResponseDTO;
import com.sp.partners.model.Partners;
import com.sp.partners.util.CommonMethods;
import org.springframework.stereotype.Component;

@Component
public class PartnersMapper {

    public static Partners toPartners(PartnersRequestDTO request) {
        Partners partners = new Partners();
        partners.setPartnerName(request.getPartnerName());
        partners.setNotes(request.getNotes());
        return partners;
    }


    public static PartnersResponseDTO toPartnersResponseDTO(Partners partners , AddressResponseDTO address) {
        PartnersResponseDTO partnersResponseDTO = new PartnersResponseDTO();

        partnersResponseDTO.setId(partners.getId());
        partnersResponseDTO.setPartnerTypeId(partners.getPartnerTypeId().intValue());
        partnersResponseDTO.setPartnerName(CommonMethods.getPartnerType(partners.getPartnerTypeId()));

        partners.setPartnerName(partners.getPartnerName());
        partnersResponseDTO.setNotes(partners.getNotes());
        partnersResponseDTO.setCreatedAt(partners.getCreatedAt());
        partnersResponseDTO.setUpdatedAt(partners.getUpdatedAt());
        partnersResponseDTO.setCreatedBy(partners.getCreatedBy());
        partnersResponseDTO.setUpdatedBy(partners.getUpdatedBy());

        partnersResponseDTO.setAddress(address);

        return partnersResponseDTO;

    }
}
