package com.martin.company_management_api.address.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressRequestDTO {
    @NotBlank(message = "Street name cannot be empty")
    private String street;
    @NotBlank(message = "City name cannot be empty")
    private String city;
    @NotBlank(message = "State cannot be empty")
    private String state;
    @NotBlank(message = "Zipcode name cannot be empty")
    private String zipcode;
}
