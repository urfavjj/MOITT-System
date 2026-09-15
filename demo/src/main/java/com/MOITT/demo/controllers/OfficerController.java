package com.MOITT.demo.controllers;

import com.MOITT.demo.dto.OfficerDTO;
import com.MOITT.demo.services.OfficerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("officer")
public class OfficerController {
    OfficerService officerService;

    @Autowired
    public OfficerController(OfficerService officerService) {
        this.officerService = officerService;
    }

    // Add API
    @PostMapping("add")
    public Long addOfficer(@Valid @RequestBody OfficerDTO dto){
        return officerService.addOfficer(
                dto.getOfficerName(),
                dto.getOfficerEmail(),
                dto.getOfficerPhoneNumber(),
                dto.getOfficerDesignation()
        );
    }


    // Get All API
    @GetMapping("getAll")
    public List<OfficerDTO> getAllOfficers(){
        return OfficerDTO.convertToDTO(officerService.getAllOfficers());
    }

    // Get By Id API
    @GetMapping("getById")
    public OfficerDTO getById(@RequestParam Long id){
        return OfficerDTO.convertToDTO(officerService.getById(id));
    }



    // Update API
    @PutMapping("update")
    public OfficerDTO updateOfficer(@Valid @RequestBody OfficerDTO dto){
        return OfficerDTO.convertToDTO(
                officerService.updatedOfficer(
                        dto.getOfficerId(),
                        dto.getOfficerName(),
                        dto.getOfficerEmail(),
                        dto.getOfficerPhoneNumber(),
                        dto.getOfficerDesignation()
                )
        );
    }



    // Delete API
    @DeleteMapping("delete")
    public Boolean deleteOfficerById(@RequestParam Long id){
        return officerService.deleteById(id);
    }
}