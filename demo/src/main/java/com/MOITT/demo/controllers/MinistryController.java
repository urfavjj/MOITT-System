package com.MOITT.demo.controllers;

import com.MOITT.demo.dto.MinistryDTO;
import com.MOITT.demo.services.MinistryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ministry")
public class MinistryController {
    MinistryService ministryService;

    @Autowired
    public MinistryController(MinistryService ministryService) {
        this.ministryService = ministryService;
    }


    // Add API
    @PostMapping("add")
    public Long addMinistry(@Valid @RequestBody MinistryDTO dto){
        return ministryService.addMinistry(
                dto.getMinistryName(),
                dto.getMinistryAddress()
        );
    }

    // Get All API
    @GetMapping("getAll")
    public List<MinistryDTO> getAllMinistries(){
        return MinistryDTO.convertToDTO(ministryService.getAllMinistries()
        );
    }


    // Get By Id API
    @GetMapping("getById")
    public MinistryDTO getById(@RequestParam Long id){
        return MinistryDTO.convertToDTO(ministryService.getById(id));
    }

    // Update API
    @PutMapping("update")
    public MinistryDTO updateMinistry(@Valid @RequestBody MinistryDTO dto){
        return MinistryDTO.convertToDTO(
                ministryService.updatedMinistry(
                        dto.getMinistryId(),
                        dto.getMinistryName(),
                        dto.getMinistryAddress()
                )
        );
    }


    // Delete API
    @DeleteMapping("delete")
    public Boolean deleteMinistryById(@RequestParam Long id){
        return ministryService.deleteById(id);
    }
}
