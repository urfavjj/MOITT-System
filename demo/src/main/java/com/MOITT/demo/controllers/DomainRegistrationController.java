package com.MOITT.demo.controllers;

import com.MOITT.demo.dto.DomainRegistrationDTO;
import com.MOITT.demo.services.DomainRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("domainRegistration")
public class DomainRegistrationController {

    DomainRegistrationService domainRegistrationService;

    @Autowired
    public DomainRegistrationController(DomainRegistrationService domainRegistrationService) {
        this.domainRegistrationService = domainRegistrationService;
    }

    // Add API
    @PostMapping("add")
    public Long addDomainRegistration(@Valid @RequestBody DomainRegistrationDTO dto) {
        return domainRegistrationService.addDomainRegistration(
                dto.getDomainName(),
                dto.getRegisteredDate(),
                dto.getExpiryDate(),
                dto.getDomainStatus()
        );
    }

    // Get All API
    @GetMapping("getAll")
    public List<DomainRegistrationDTO> getAllDomainRegistrations() {
        return DomainRegistrationDTO.convertToDTO(
                domainRegistrationService.getAllDomainRegistrations()
        );
    }

    // Get By Id API
    @GetMapping("getById")
    public DomainRegistrationDTO getById(@RequestParam Long id) {
        return DomainRegistrationDTO.convertToDTO(
                domainRegistrationService.getById(id)
        );
    }

    // Update API
    @PutMapping("update")
    public DomainRegistrationDTO updateDomainRegistration(@Valid @RequestBody DomainRegistrationDTO dto) {
        return DomainRegistrationDTO.convertToDTO(
                domainRegistrationService.updatedDomainRegistration(
                        dto.getDomainRegistrationId(),
                        dto.getDomainName(),
                        dto.getRegisteredDate(),
                        dto.getExpiryDate(),
                        dto.getDomainStatus()
                )
        );
    }

    // Register Domain API
    @PostMapping("register")
    public Long registerDomain(@RequestParam Long citizenId, @RequestParam String domainName,
            @RequestParam
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            Date registeredDate,
            @RequestParam
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            Date expiryDate) {
        return domainRegistrationService.registerDomain(citizenId, domainName, registeredDate, expiryDate
        );
    }

    // Renew Domain API
    @PutMapping("renew")
    public Boolean renewDomain(@RequestParam Long domainId,
            @RequestParam
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            Date newExpiryDate) {
        return domainRegistrationService.renewDomain(domainId, newExpiryDate);
    }

    // Delete API
    @DeleteMapping("delete")
    public Boolean deleteDomainRegistrationById(@RequestParam Long id) {
        return domainRegistrationService.deleteById(id);
    }
}