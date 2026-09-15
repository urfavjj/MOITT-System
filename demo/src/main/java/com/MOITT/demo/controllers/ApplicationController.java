package com.MOITT.demo.controllers;

import com.MOITT.demo.dto.ApplicationDTO;
import com.MOITT.demo.entities.Application;
import com.MOITT.demo.entities.ApplicationStatus;
import com.MOITT.demo.services.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("application")
public class ApplicationController {
    ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    // Add API
    @PostMapping("add")
    public Long addApplication(@Valid @RequestBody ApplicationDTO dto) {
        Application application = new Application();
        application.setApplicationDate(dto.getApplicationDate());
        application.setStatus(dto.getApplicationStatus());
        application.setReferenceNumber(dto.getApplicationReferenceNumber());
        return applicationService.addApplication(application);
    }


    // Get All API
    @GetMapping("getAll")
    public List<ApplicationDTO> getAllApplications(){
        return ApplicationDTO.convertToDTO(applicationService.getAllApplications());
    }


    // Get By Id API
    @GetMapping("getById")
    public ApplicationDTO getById(@RequestParam Long id){
        return ApplicationDTO.convertToDTO(applicationService.getById(id));
    }


    // Update API
    @PutMapping("update")
    public ApplicationDTO updateApplication(@Valid @RequestBody ApplicationDTO dto) {
        Application application = new Application();
        application.setApplicationDate(dto.getApplicationDate());
        application.setStatus(dto.getApplicationStatus());
        application.setReferenceNumber(dto.getApplicationReferenceNumber());
        return ApplicationDTO.convertToDTO(applicationService.updatedApplication(
                dto.getApplicationId(),
                application)
        );
    }


    // Delete API
    @DeleteMapping("delete")
    public Boolean deleteApplicationById(@RequestParam Long id){
        return applicationService.deleteById(id);
    }

    //Submit API
    @PostMapping("submit")
    public Long submitApplication(@RequestParam Long citizenId, @RequestParam Long serviceId, @RequestParam Long officerId){
        return applicationService.submitApplication(citizenId, serviceId, officerId);
    }

    //Approve / Reject API
    @PutMapping("process")
    public Boolean processApplicationDecision(@RequestParam Long applicationId, @RequestParam Long officerId, @RequestParam String decision, @RequestParam String documentTitle){
        return applicationService.processApplicationDecision(applicationId, officerId, decision, documentTitle);
    }

    //Status API
    @GetMapping("status")
    public List<ApplicationDTO> getApplicationsByStatus(@RequestParam ApplicationStatus status){
        return ApplicationDTO.convertToDTO(
                applicationService.getApplicationsByStatus(status)
        );

    }

    //Citizen History API
    @GetMapping("citizenHistory")
    public List<ApplicationDTO> getCitizenApplicationHistory(
            @RequestParam Long citizenId
    ){

        return ApplicationDTO.convertToDTO(
                applicationService.getCitizenApplicationHistory(citizenId)
        );

    }
}