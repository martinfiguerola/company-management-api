package com.martin.company_management_api.address.service;

import com.martin.company_management_api.address.dto.AddressDetailDTO;
import com.martin.company_management_api.address.dto.AddressRequestDTO;
import com.martin.company_management_api.address.dto.AddressResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AddressService {

    Page<AddressResponseDTO> findAll (Pageable pageable);
    Optional<AddressDetailDTO> findById (Long id);
    AddressResponseDTO save (AddressRequestDTO addressRequestDTO);
    Optional<AddressResponseDTO> update (Long id, AddressRequestDTO addressRequestDTO);
    Boolean deleteById (Long id);
}
