package com.MOITT.demo.dto;

import com.MOITT.demo.entities.DomainRegistration;
import com.MOITT.demo.entities.DomainStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class DomainRegistrationDTO {
    private Long domainRegistrationId;

    @NotBlank(message = "You can't leave the domain name to be null")
    @Size(min = 3, max = 255, message = "Domain name has to be between 3 to 255 characters")
    private String domainName;

    private Date registeredDate;
    private Date expiryDate;

    private DomainStatus domainStatus;


    public static DomainRegistrationDTO convertToDTO(DomainRegistration entity){
        DomainRegistrationDTO dto = DomainRegistrationDTO.builder()
                        .domainRegistrationId(entity.getId())
                        .domainName(entity.getDomainName())
                        .registeredDate(entity.getRegisteredDate())
                        .expiryDate(entity.getExpiryDate())
                        .domainStatus(entity.getStatus())
                        .build();
        return dto;
    }

    public static List<DomainRegistrationDTO> convertToDTO(List<DomainRegistration> entityList){
        List<DomainRegistrationDTO> dtos = new ArrayList<>();
        for(DomainRegistration domain : entityList){
            dtos.add(convertToDTO(domain));
        }
        return dtos;
    }
}