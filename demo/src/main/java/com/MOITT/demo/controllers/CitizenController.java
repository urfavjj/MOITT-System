package com.MOITT.demo.controllers;

import com.MOITT.demo.dto.CitizenDTO;
import com.MOITT.demo.services.CitizenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("citizen")
public class CitizenController {
    CitizenService citizenService;

    @Autowired
    public CitizenController(CitizenService citizenService) {this.citizenService = citizenService;}

    // Add API
    @PostMapping("add")
    public Long addCitizen(@Valid @RequestBody CitizenDTO dto){
        return citizenService.addCitizen(
                dto.getCitizenName(),
                dto.getCitizenNationalId(),
                dto.getCitizenPhoneNumber(),
                dto.getCitizenEmail()
        );
    }

    // Get All API
    @GetMapping("getAll")
    public List<CitizenDTO> getAllCitizens(){
        return CitizenDTO.convertToDTO(
                citizenService.getAllCitizens()
        );
    }

    // Get By Id API
    @GetMapping("getById")
    public CitizenDTO getById(@RequestParam Long id){
        return CitizenDTO.convertToDTO(
                citizenService.getById(id)
        );
    }

    // Update API
    @PutMapping("update")
    public CitizenDTO updateCitizen(@Valid @RequestBody CitizenDTO dto){
        return CitizenDTO.convertToDTO(
                citizenService.updatedCitizen(
                        dto.getCitizenId(),
                        dto.getCitizenName(),
                        dto.getCitizenNationalId(),
                        dto.getCitizenPhoneNumber(),
                        dto.getCitizenEmail()
                )
        );
    }

    // Delete API
    @DeleteMapping("delete")
    public Boolean deleteCitizenById(@RequestParam Long id){
        return citizenService.deleteById(id);
    }
}