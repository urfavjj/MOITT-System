package com.MOITT.demo.controllers;

import com.MOITT.demo.dto.OperatorDTO;
import com.MOITT.demo.services.OperatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("operator")
public class OperatorController {
    OperatorService operatorService;

    @Autowired
    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    // Add API
    @PostMapping("add")
    public Long addOperator(@Valid @RequestBody OperatorDTO dto){
        return operatorService.addOperator(
                dto.getOperatorName(),
                dto.getOperatorLicenseNumber(),
                dto.getOperatorContactEmail(),
                dto.getOperatorCountry()
        );
    }


    // Get All API
    @GetMapping("getAll")
    public List<OperatorDTO> getAllOperators(){
        return OperatorDTO.convertToDTO(operatorService.getAllOperators());
    }

    // Get By Id API
    @GetMapping("getById")
    public OperatorDTO getById(@RequestParam Long id){
        return OperatorDTO.convertToDTO(
                operatorService.getById(id)
        );
    }


    // Update API
    @PutMapping("update")
    public OperatorDTO updateOperator(@Valid @RequestBody OperatorDTO dto){
        return OperatorDTO.convertToDTO(
                operatorService.updatedOperator(
                        dto.getOperatorId(),
                        dto.getOperatorName(),
                        dto.getOperatorLicenseNumber(),
                        dto.getOperatorContactEmail(),
                        dto.getOperatorCountry()
                )
        );
    }

    //Operator active licenses API
    @GetMapping("/{id}/licenses")
    public Long activeLicenses(@PathVariable Long id){
        return operatorService.getActiveLicensesCount(id);
    }

    // Operator open complaints API
    @GetMapping("/{id}/complaints")
    public Long openComplaints(@PathVariable Long id){
        return operatorService.getOpenComplaintsCount(id);
    }


    // Delete API
    @DeleteMapping("delete")
    public Boolean deleteOperatorById(@RequestParam Long id){
        return operatorService.deleteById(id);
    }
}