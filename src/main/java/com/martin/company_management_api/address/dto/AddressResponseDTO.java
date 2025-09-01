package com.martin.company_management_api.address.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressResponseDTO {

    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipcode;
}
