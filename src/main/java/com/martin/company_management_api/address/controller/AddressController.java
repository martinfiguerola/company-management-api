package com.martin.company_management_api.address.controller;

import com.martin.company_management_api.address.dto.AddressRequestDTO;
import com.martin.company_management_api.address.dto.AddressResponseDTO;
import com.martin.company_management_api.address.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/addresses")
public class AddressController{

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<Page<AddressResponseDTO>> getAddresses (Pageable pageable) {
        Page<AddressResponseDTO> responseDTOPage = addressService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTOPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> getAddress (@PathVariable Long id) {
        Optional<AddressResponseDTO> responseDTOOptional = addressService.findById(id);

        return responseDTOOptional.map(addressResponseDTO -> ResponseEntity.status(HttpStatus.OK).body(addressResponseDTO))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress (@PathVariable Long id) {
        if (addressService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address with given ID does not exist.");
    }

    @PostMapping
    public ResponseEntity<AddressResponseDTO> createAddress (@Valid @RequestBody AddressRequestDTO addressRequestDTO) {
        AddressResponseDTO responseDTO = addressService.save(addressRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress (@PathVariable Long id, @Valid @RequestBody AddressRequestDTO addressRequestDTO) {
        Optional<AddressResponseDTO> optionalAddressResponseDTO = addressService.update(id, addressRequestDTO);
        return optionalAddressResponseDTO
                .map(addressResponseDTO -> ResponseEntity.status(HttpStatus.OK).body(addressResponseDTO))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
