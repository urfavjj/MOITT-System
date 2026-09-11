package com.MOITT.demo.dto;

import com.MOITT.demo.entities.LicenseStatus;
import com.MOITT.demo.entities.SpectrumLicense;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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
public class SpectrumLicenseDTO {
    private Long spectrumLicenseId;

    @NotBlank(message = "You can't leave the band name to be null")
    private String spectrumLicenseBandName;

    @Positive(message = "Frequency must be greater than zero")
    private Double spectrumLicenseFrequencyMhz;

    private Date spectrumLicenseIssueDate;
    private Date spectrumLicenseExpiryDate;
    private LicenseStatus spectrumLicenseStatus;


    public static SpectrumLicenseDTO convertToDTO(SpectrumLicense entity){
        SpectrumLicenseDTO dto = SpectrumLicenseDTO.builder()
                        .spectrumLicenseId(entity.getId())
                        .spectrumLicenseBandName(entity.getBandName())
                        .spectrumLicenseFrequencyMhz(entity.getFrequencyMhz())
                        .spectrumLicenseIssueDate(entity.getIssueDate())
                        .spectrumLicenseExpiryDate(entity.getExpiryDate())
                        .spectrumLicenseStatus(entity.getStatus())
                        .build();
        return dto;
    }


    public static List<SpectrumLicenseDTO> convertToDTO(List<SpectrumLicense> entityList){
        List<SpectrumLicenseDTO> dtos = new ArrayList<>();
        for(SpectrumLicense license : entityList){
            dtos.add(convertToDTO(license));
        }
        return dtos;
    }
}