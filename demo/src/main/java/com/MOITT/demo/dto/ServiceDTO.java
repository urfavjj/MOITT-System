package com.MOITT.demo.dto;

import com.MOITT.demo.entities.Service;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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
public class ServiceDTO {
    private Long serviceId;

    @NotBlank(message = "You can't leave the service name to be null")
    @Size(min = 3, max = 100, message = "Service name has to be between 3 to 100 characters"
    )
    private String serviceName;

    @Size(max = 500, message = "Service description cannot exceed 500 characters")
    private String serviceDescription;

    @Positive(message = "Service fee must be greater than zero")
    private Double serviceFee;


    @Positive(message = "Processing days must be greater than zero")
    private Integer serviceProcessingDays;


    public static ServiceDTO convertToDTO(Service entity){
        ServiceDTO dto = ServiceDTO.builder()
                .serviceId(entity.getId())
                .serviceName(entity.getName())
                .serviceDescription(entity.getDescription())
                .serviceFee(entity.getFee())
                .serviceProcessingDays(entity.getProcessingDays())
                .build();
        return dto;
    }

    public static List<ServiceDTO> convertToDTO(List<Service> entityList){
        List<ServiceDTO> dtos = new ArrayList<>();
        for(Service service : entityList){

            dtos.add(convertToDTO(service));
        }
        return dtos;
    }
}