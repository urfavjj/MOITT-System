package com.MOITT.demo.dto;

import com.MOITT.demo.entities.Citizen;
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
public class CitizenDTO {
    private Long citizenId;

    @NotBlank(message = "You can't leave the citizen name to be null")
    @Size(min = 3, max = 100, message = "Citizen name has to be between 3 to 100 characters")
    private String citizenName;


    @NotBlank(message = "You can't leave the national ID to be null")
    @Size(min = 5, max = 20, message = "National ID has to be between 5 to 20 characters")
    private String citizenNationalId;


    @NotBlank(message = "You can't leave the phone number to be null")
    @Size(min = 7, max = 20, message = "Phone number has to be between 7 to 20 characters")
    private String citizenPhoneNumber;


    @NotBlank(message = "You can't leave the email to be null")
    @Email(message = "Please enter a valid email")
    @Size(max = 150, message = "Email cannot exceed 150 characters")
    private String citizenEmail;



    public static CitizenDTO convertToDTO(Citizen entity){
        CitizenDTO dto = CitizenDTO.builder()
                .citizenId(entity.getId())
                .citizenName(entity.getName())
                .citizenNationalId(entity.getNationalId())
                .citizenPhoneNumber(entity.getPhoneNumber())
                .citizenEmail(entity.getEmail())
                .build();
        return dto;
    }

    public static List<CitizenDTO> convertToDTO(List<Citizen> entityList){
        List<CitizenDTO> dtos = new ArrayList<>();
        for(Citizen citizen : entityList){
            dtos.add(convertToDTO(citizen));
        }
        return dtos;
    }
}