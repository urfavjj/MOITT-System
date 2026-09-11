package com.MOITT.demo.dto;

import com.MOITT.demo.entities.Officer;
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
public class OfficerDTO {

    private Long officerId;

    @NotBlank(message = "You can't leave the officer name to be null")
    @Size(min = 3, max = 100, message = "Officer name has to be between 3 to 100 characters")
    private String officerName;


    @NotBlank(message = "You can't leave the officer email to be null")
    @Email(message = "Please enter a valid email")
    @Size(max = 150, message = "Officer email cannot exceed 150 characters")
    private String officerEmail;


    @NotBlank(message = "You can't leave the officer phone number to be null")
    @Size(min = 7, max = 20, message = "Phone number has to be between 7 to 20 characters")
    private String officerPhoneNumber;

    @NotBlank(message = "You can't leave the officer designation to be null")
    @Size(min = 2, max = 100, message = "Designation has to be between 2 to 100 characters")
    private String officerDesignation;

    public static OfficerDTO convertToDTO(Officer entity){
        OfficerDTO dto = OfficerDTO.builder()
                .officerId(entity.getId())
                .officerName(entity.getName())
                .officerEmail(entity.getEmail())
                .officerPhoneNumber(entity.getPhoneNumber())
                .officerDesignation(entity.getDesignation())
                .build();
        return dto;
    }

    public static List<OfficerDTO> convertToDTO(List<Officer> entityList){
        List<OfficerDTO> dtos = new ArrayList<>();
        for(Officer officer : entityList){
            dtos.add(convertToDTO(officer));
        }
        return dtos;
    }
}