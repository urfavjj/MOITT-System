package com.MOITT.demo.dto;

import com.MOITT.demo.entities.Operator;
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
public class OperatorDTO {
    private Long operatorId;

    @NotBlank(message = "You can't leave the operator name to be null")
    @Size(min = 3, max = 100, message = "Operator name has to be between 3 to 100 characters")
    private String operatorName;

    @NotBlank(message = "You can't leave the license number to be null")
    @Size(min = 3, max = 100, message = "License number has to be between 3 to 100 characters")
    private String operatorLicenseNumber;

    @NotBlank(message = "You can't leave the contact email to be null")
    @Email(message = "Please enter a valid email")
    @Size(max = 150, message = "Contact email cannot exceed 150 characters")
    private String operatorContactEmail;

    @NotBlank(message = "You can't leave the country to be null")
    @Size(min = 2, max = 100, message = "Country has to be between 2 to 100 characters")
    private String operatorCountry;

    public static OperatorDTO convertToDTO(Operator entity){
        OperatorDTO dto = OperatorDTO.builder()
                .operatorId(entity.getId())
                .operatorName(entity.getName())
                .operatorLicenseNumber(entity.getLicenseNumber())
                .operatorContactEmail(entity.getContactEmail())
                .operatorCountry(entity.getCountry())
                .build();
        return dto;
    }


    public static List<OperatorDTO> convertToDTO(List<Operator> entityList){
        List<OperatorDTO> dtos = new ArrayList<>();
        for(Operator operator : entityList){
            dtos.add(convertToDTO(operator));
        }
        return dtos;
    }
}