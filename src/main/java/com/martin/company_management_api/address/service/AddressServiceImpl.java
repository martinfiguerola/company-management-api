package com.martin.company_management_api.address.service;


import com.martin.company_management_api.address.dto.AddressDetailDTO;
import com.martin.company_management_api.address.dto.AddressRequestDTO;
import com.martin.company_management_api.address.dto.AddressResponseDTO;
import com.martin.company_management_api.address.mapper.AddressMapperDTO;
import com.martin.company_management_api.address.model.Address;
import com.martin.company_management_api.address.repository.AddressRepository;
import com.martin.company_management_api.employee.model.Employee;
import com.martin.company_management_api.employee.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;

    public AddressServiceImpl(AddressRepository addressRepository, EmployeeRepository employeeRepository) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<AddressResponseDTO> findAll(Pageable pageable) {

        Page<Address> addressPage = addressRepository.findAll(pageable);

        return addressPage.map(AddressMapperDTO::toDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<AddressDetailDTO> findById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.map(AddressMapperDTO::addressDetailDTO);
    }

    @Transactional
    @Override
    public AddressResponseDTO save(AddressRequestDTO addressRequestDTO) {

        Long employeeId = addressRequestDTO.getEmployee();

        Address address = AddressMapperDTO.fromDTO(addressRequestDTO);

        Employee employee = employeeRepository.findById(employeeId)
                       .orElseThrow(() -> new IllegalArgumentException("Employee with: " + employeeId + " does not exist."));

        address.setEmployee(employee);

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

        // 1. Find the address to be deleted
        Optional<Address> optionalAddress = addressRepository.findById(id);

        return optionalAddress.map(address -> {
            // 2. Terminate the relationship with the Employee
            Employee employee = address.getEmployee();
            if (employee != null) {
                employee.setAddress(null);
                // Save the Employee to break the relationship in the DB
                employeeRepository.save(employee);
            }
            // 3. Delete the Address
            addressRepository.delete(address);
            return true;

        }).orElse(false);
    }
}
