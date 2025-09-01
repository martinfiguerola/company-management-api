package com.martin.company_management_api.address.service;


import com.martin.company_management_api.address.dto.AddressRequestDTO;
import com.martin.company_management_api.address.dto.AddressResponseDTO;
import com.martin.company_management_api.address.mapper.AddressMapperDTO;
import com.martin.company_management_api.address.model.Address;
import com.martin.company_management_api.address.repository.AddressRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<AddressResponseDTO> findAll(Pageable pageable) {

        Page<Address> addressPage = addressRepository.findAll(pageable);

        return addressPage.map(AddressMapperDTO::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<AddressResponseDTO> findById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.map(AddressMapperDTO::toDTO);
    }

    @Transactional
    @Override
    public AddressResponseDTO save(AddressRequestDTO addressRequestDTO) {
        Address address = AddressMapperDTO.fromDTO(addressRequestDTO);

        Address savedAddress = addressRepository.save(address);

        return AddressMapperDTO.toDTO(savedAddress);
    }

    @Transactional
    @Override
    public Optional<AddressResponseDTO> update(Long id, AddressRequestDTO addressRequestDTO) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.map(address -> {
            address.setStreet(addressRequestDTO.getStreet());
            address.setCity(addressRequestDTO.getCity());
            address.setState(addressRequestDTO.getState());
            address.setZipcode(addressRequestDTO.getZipcode());

            Address updatedAddress = addressRepository.save(address);

            return AddressMapperDTO.toDTO(updatedAddress);
        });
    }

    @Transactional
    @Override
    public Boolean deleteById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);

        return optionalAddress.map(address -> {
            addressRepository.delete(address);
            return true;
        }).orElse(false);
    }
}
