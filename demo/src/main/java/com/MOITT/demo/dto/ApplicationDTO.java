package com.MOITT.demo.dto;

import com.MOITT.demo.entities.Application;
import com.MOITT.demo.entities.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {
    private Long applicationId;
    private Date applicationDate;
    private ApplicationStatus applicationStatus;

    @NotBlank(message = "You can't leave the reference number to be null")
    private String applicationReferenceNumber;

    public static ApplicationDTO convertToDTO(Application entity){
        ApplicationDTO dto = ApplicationDTO.builder()
                .applicationId(entity.getId())
                .applicationDate(entity.getApplicationDate())
                .applicationStatus(entity.getStatus())
                .applicationReferenceNumber(entity.getReferenceNumber())
                .build();
        return dto;
    }

    public static List<ApplicationDTO> convertToDTO(List<Application> entityList){
        List<ApplicationDTO> dtos = new ArrayList<>();
        for(Application application : entityList){

            dtos.add(convertToDTO(application));
        }
        return dtos;
    }
}
