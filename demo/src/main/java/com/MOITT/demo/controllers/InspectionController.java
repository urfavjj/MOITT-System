package com.MOITT.demo.controllers;

import com.MOITT.demo.dto.InspectionDTO;
import com.MOITT.demo.services.InspectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("inspection")
public class InspectionController {
    InspectionService inspectionService;

    @Autowired
    public InspectionController(InspectionService inspectionService) {
        this.inspectionService = inspectionService;
    }

    // Add API
    @PostMapping("add")
    public Long addInspection(@Valid @RequestBody InspectionDTO dto){
        return inspectionService.addInspection(
                dto.getInspectionDate(),
                dto.getInspectionResult(),
                dto.getInspectionNotes()
        );
    }



    // Get All API
    @GetMapping("getAll")
    public List<InspectionDTO> getAllInspections(){
        return InspectionDTO.convertToDTO(inspectionService.getAllInspections());
    }


    // Get By Id API
    @GetMapping("getById")
    public InspectionDTO getById(@RequestParam Long id){
        return InspectionDTO.convertToDTO(inspectionService.getById(id));
    }

    // Schedule API
    @PostMapping("schedule")
    public Long scheduleInspection(@RequestParam Long operatorId, @RequestParam Long officerId,
            @RequestParam
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            Date inspectionDate,
            @RequestParam String result) {
        return inspectionService.scheduleInspection(operatorId, officerId, inspectionDate, result);
    }


    // Update API
    @PutMapping("update")
    public InspectionDTO updateInspection(@Valid @RequestBody InspectionDTO dto){
        return InspectionDTO.convertToDTO(
                inspectionService.updatedInspection(
                        dto.getInspectionId(),
                        dto.getInspectionDate(),
                        dto.getInspectionResult(),
                        dto.getInspectionNotes()
                )
        );
    }



    // Delete API
    @DeleteMapping("delete")
    public Boolean deleteInspectionById(@RequestParam Long id){
        return inspectionService.deleteById(id);
    }
}