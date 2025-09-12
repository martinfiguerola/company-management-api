package com.martin.company_management_api.address.mapper;

import com.martin.company_management_api.address.dto.AddressDetailDTO;
import com.martin.company_management_api.address.dto.AddressRequestDTO;
import com.martin.company_management_api.address.dto.AddressResponseDTO;
import com.martin.company_management_api.address.model.Address;
import com.martin.company_management_api.employee.dto.EmployeeRefDTO;
import com.martin.company_management_api.employee.mapper.EmployeeMapperDTO;

public class AddressMapperDTO {

    public static Address fromDTO (AddressRequestDTO addressRequestDTO) {
        Address address = new Address();
        address.setStreet(addressRequestDTO.getStreet());
        address.setCity(addressRequestDTO.getCity());
        address.setState(addressRequestDTO.getState());
        address.setZipcode(addressRequestDTO.getZipcode());

        return address;
    }

    public static AddressResponseDTO toDTO (Address address) {
        AddressResponseDTO addressResponseDTO = new AddressResponseDTO();
        addressResponseDTO.setId(address.getId());
        addressResponseDTO.setStreet(address.getStreet());
        addressResponseDTO.setCity(address.getCity());
        addressResponseDTO.setState(address.getState());
        addressResponseDTO.setZipcode(address.getZipcode());

        return addressResponseDTO;
    }

    public static AddressDetailDTO addressDetailDTO (Address address) {
        AddressDetailDTO addressDetailDTO = new AddressDetailDTO();
        addressDetailDTO.setId(address.getId());
        addressDetailDTO.setStreet(address.getStreet());
        addressDetailDTO.setCity(address.getCity());
        addressDetailDTO.setState(address.getState());
        addressDetailDTO.setZipcode(address.getZipcode());

        EmployeeRefDTO employeeRefDTO = EmployeeMapperDTO.employeeRefDTO(address.getEmployee());

        addressDetailDTO.setEmployee(employeeRefDTO);

        return addressDetailDTO;
    }
}
