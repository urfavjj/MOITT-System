package com.MOITT.demo.controllers;

import com.MOITT.demo.dto.SpectrumLicenseDTO;
import com.MOITT.demo.services.SpectrumLicenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("spectrumLicense")
public class SpectrumLicenseController {
    SpectrumLicenseService spectrumLicenseService;

    @Autowired
    public SpectrumLicenseController(SpectrumLicenseService spectrumLicenseService) {
        this.spectrumLicenseService = spectrumLicenseService;
    }

    // Add API
    @PostMapping("add")
    public Long addSpectrumLicense(@Valid @RequestBody SpectrumLicenseDTO dto){
        return spectrumLicenseService.addSpectrumLicense(
                dto.getSpectrumLicenseBandName(),
                dto.getSpectrumLicenseFrequencyMhz(),
                dto.getSpectrumLicenseIssueDate(),
                dto.getSpectrumLicenseExpiryDate(),
                dto.getSpectrumLicenseStatus()
        );
    }

    // Issue License API
    @PostMapping("issue")
    public Long issueLicense(@RequestParam Long operatorId, @RequestParam String bandName, @RequestParam Double frequencyMhz,
            @RequestParam
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            Date issueDate,
            @RequestParam
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            Date expiryDate) {
        return spectrumLicenseService.issueLicense(operatorId, bandName, frequencyMhz, issueDate, expiryDate);
    }

    //Expiring licenses API
    @GetMapping("expiringSoon")
    public List<SpectrumLicenseDTO> getExpiringSoon(){

        return SpectrumLicenseDTO.convertToDTO(
                spectrumLicenseService.getLicensesExpiringSoon()
        );

    }

    // Get All API
    @GetMapping("getAll")
    public List<SpectrumLicenseDTO> getAllSpectrumLicenses(){
        return SpectrumLicenseDTO.convertToDTO(spectrumLicenseService.getAllSpectrumLicenses());
    }



    // Get By Id API
    @GetMapping("getById")
    public SpectrumLicenseDTO getById(@RequestParam Long id){
        return SpectrumLicenseDTO.convertToDTO(spectrumLicenseService.getById(id));
    }



    // Update API
    @PutMapping("update")
    public SpectrumLicenseDTO updateSpectrumLicense(@Valid @RequestBody SpectrumLicenseDTO dto){
        return SpectrumLicenseDTO.convertToDTO(
                spectrumLicenseService.updatedSpectrumLicense(
                        dto.getSpectrumLicenseId(),
                        dto.getSpectrumLicenseBandName(),
                        dto.getSpectrumLicenseFrequencyMhz(),
                        dto.getSpectrumLicenseIssueDate(),
                        dto.getSpectrumLicenseExpiryDate(),
                        dto.getSpectrumLicenseStatus()
                )
        );
    }



    // Delete API
    @DeleteMapping("delete")
    public Boolean deleteSpectrumLicenseById(@RequestParam Long id){
        return spectrumLicenseService.deleteById(id);
    }
}