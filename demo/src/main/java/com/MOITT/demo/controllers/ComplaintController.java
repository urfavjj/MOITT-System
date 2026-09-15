package com.MOITT.demo.controllers;

import com.MOITT.demo.dto.ComplaintDTO;
import com.MOITT.demo.services.ComplaintService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("complaint")
public class ComplaintController {
    ComplaintService complaintService;

    @Autowired
    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    // Add API
    @PostMapping("add")
    public Long addComplaint(@Valid @RequestBody ComplaintDTO dto){
        return complaintService.addComplaint(
                dto.getComplaintSubject(),
                dto.getComplaintDescription(),
                dto.getComplaintStatus(),
                dto.getComplaintFiledDate()
        );
    }



    // Get All API
    @GetMapping("getAll")
    public List<ComplaintDTO> getAllComplaints(){
        return ComplaintDTO.convertToDTO(complaintService.getAllComplaints());
    }


    //File API
    @PostMapping("file")
    public Long fileComplaint(@RequestParam Long citizenId, @RequestParam Long operatorId, @RequestParam Long officerId, @RequestParam String subject, @RequestParam String description){
        return complaintService.fileComplaint(
                citizenId,
                operatorId,
                officerId,
                subject,
                description
        );

    }

    //Resolve API
    @PutMapping("resolve")
    public Boolean resolveComplaint(@RequestParam Long complaintId){

        return complaintService.resolveComplaint(
                complaintId
        );

    }


    // Open complaints by operator API
    @GetMapping("/operator/{id}/open")
    public List<ComplaintDTO> getOpenComplaintsByOperator(@PathVariable Long id) {
        return ComplaintDTO.convertToDTO(
                complaintService.getOpenComplaintsByOperator(id)
        );
    }



    // Get By Id API
    @GetMapping("getById")
    public ComplaintDTO getById(@RequestParam Long id){
        return ComplaintDTO.convertToDTO(complaintService.getById(id));
    }



    // Update API
    @PutMapping("update")
    public ComplaintDTO updateComplaint(@Valid @RequestBody ComplaintDTO dto){
        return ComplaintDTO.convertToDTO(
                complaintService.updatedComplaint(
                        dto.getComplaintId(),
                        dto.getComplaintSubject(),
                        dto.getComplaintDescription(),
                        dto.getComplaintStatus(),
                        dto.getComplaintFiledDate()
                )
        );
    }



    // Delete API
    @DeleteMapping("delete")
    public Boolean deleteComplaintById(@RequestParam Long id){
        return complaintService.deleteById(id);
    }
}