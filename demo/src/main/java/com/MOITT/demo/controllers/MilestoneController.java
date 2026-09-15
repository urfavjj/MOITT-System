package com.MOITT.demo.controllers;

import com.MOITT.demo.dto.MilestoneDTO;
import com.MOITT.demo.services.MilestoneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("milestone")
public class MilestoneController {
    MilestoneService milestoneService;

    @Autowired
    public MilestoneController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }


    // Add API
    @PostMapping("add")
    public Long addMilestone(@Valid @RequestBody MilestoneDTO dto){
        return milestoneService.addMilestone(
                dto.getMilestoneTitle(),
                dto.getMilestoneDueDate(),
                dto.getMilestoneStatus()
        );
    }

    //Complete milestone API
    @PutMapping("complete")
    public Boolean completeMilestone(@RequestParam Long milestoneId){
        return milestoneService.completeMilestone(
                milestoneId
        );
    }

    // Get All API
    @GetMapping("getAll")
    public List<MilestoneDTO> getAllMilestones(){
        return MilestoneDTO.convertToDTO(milestoneService.getAllMilestones());
    }



    // Get By Id API
    @GetMapping("getById")
    public MilestoneDTO getById(@RequestParam Long id){
        return MilestoneDTO.convertToDTO(milestoneService.getById(id));
    }



    // Update API
    @PutMapping("update")
    public MilestoneDTO updateMilestone(@Valid @RequestBody MilestoneDTO dto){
        return MilestoneDTO.convertToDTO(
                milestoneService.updatedMilestone(
                        dto.getMilestoneId(),
                        dto.getMilestoneTitle(),
                        dto.getMilestoneDueDate(),
                        dto.getMilestoneStatus()
                )
        );
    }



    // Delete API
    @DeleteMapping("delete")
    public Boolean deleteMilestoneById(@RequestParam Long id){
        return milestoneService.deleteById(id);
    }
}