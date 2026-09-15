package com.MOITT.demo.controllers;

import com.MOITT.demo.dto.DepartmentDTO;
import com.MOITT.demo.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("department")
public class DepartmentController {
    DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Add API
    @PostMapping("add")
    public Long addDepartment(@Valid @RequestBody DepartmentDTO dto){
        return departmentService.addDepartment(
                dto.getDepartmentName(),
                dto.getDepartmentDescription()
        );
    }

    // Get All API
    @GetMapping("getAll")
    public List<DepartmentDTO> getAllDepartments(){
        return DepartmentDTO.convertToDTO(departmentService.getAllDepartments());
    }

    // Get By Id API
    @GetMapping("getById")
    public DepartmentDTO getById(@RequestParam Long id){
        return DepartmentDTO.convertToDTO(
                departmentService.getById(id)
        );
    }

    // Update API
    @PutMapping("update")
    public DepartmentDTO updateDepartment(@Valid @RequestBody DepartmentDTO dto){
        return DepartmentDTO.convertToDTO(
                departmentService.updatedDepartment(
                        dto.getDepartmentId(),
                        dto.getDepartmentName(),
                        dto.getDepartmentDescription()
                )
        );
    }

    // Department pending applications API
    @GetMapping("/{id}/pending")
    public Long pendingApplications(@PathVariable Long id){
        return departmentService.getPendingApplicationsCount(id);
    }

    // Department officer workload API
    @GetMapping("/{id}/officerWorkload")
    public Long officerWorkload(@PathVariable Long id){
        return departmentService.getOfficerWorkload(id);
    }

    // Delete API
    @DeleteMapping("delete")
    public Boolean deleteDepartmentById(@RequestParam Long id){
        return departmentService.deleteById(id);
    }
}