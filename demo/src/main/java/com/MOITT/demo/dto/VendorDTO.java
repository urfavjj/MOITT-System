package com.MOITT.demo.dto;

import com.MOITT.demo.entities.Vendor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {
    private Long vendorId;

    @NotBlank(message = "You can't leave the vendor name to be null")
    @Size(min = 3, max = 100, message = "Vendor name has to be between 3 to 100 characters")
    private String vendorName;


    @NotBlank(message = "You can't leave the vendor email to be null")
    @Email(message = "Please enter a valid email")
    @Size(max = 150, message = "Vendor email cannot exceed 150 characters")
    private String vendorContactEmail;


    @NotBlank(message = "You can't leave the phone number to be null")
    @Size(min = 7, max = 20, message = "Phone number has to be between 7 to 20 characters")
    private String vendorPhoneNumber;


    @NotBlank(message = "You can't leave the country to be null")
    @Size(min = 2, max = 100, message = "Country has to be between 2 to 100 characters")
    private String vendorCountry;


    public static VendorDTO convertToDTO(Vendor entity){
        VendorDTO dto = VendorDTO.builder()
                .vendorId(entity.getId())
                .vendorName(entity.getName())
                .vendorContactEmail(entity.getContactEmail())
                .vendorPhoneNumber(entity.getPhoneNumber())
                .vendorCountry(entity.getCountry())
                .build();
        return dto;
    }



    public static List<VendorDTO> convertToDTO(List<Vendor> entityList){
        List<VendorDTO> dtos = new ArrayList<>();
        for(Vendor vendor : entityList){
            dtos.add(convertToDTO(vendor));
        }
        return dtos;
    }
}