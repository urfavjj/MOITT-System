package com.MOITT.demo.services;

import com.MOITT.demo.entities.*;
import com.MOITT.demo.repositories.CitizenRepository;
import com.MOITT.demo.repositories.ComplaintRepository;
import com.MOITT.demo.repositories.OfficerRepository;
import com.MOITT.demo.repositories.OperatorRepository;
import com.MOITT.demo.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {

    ComplaintRepository complaintRepository;
    CitizenRepository citizenRepository;
    OperatorRepository operatorRepository;
    OfficerRepository officerRepository;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository, CitizenRepository citizenRepository, OperatorRepository operatorRepository, OfficerRepository officerRepository) {
        this.complaintRepository = complaintRepository;
        this.citizenRepository = citizenRepository;
        this.operatorRepository = operatorRepository;
        this.officerRepository = officerRepository;
    }

    // Add service
    public Long addComplaint(String subject, String description, ComplaintStatus status, Date filedDate) {
        Complaint complaint = new Complaint();
        complaint.setIsActive(true);
        complaint.setCreatedDate(new Date());
        complaint.setSubject(subject);
        complaint.setDescription(description);
        complaint.setStatus(status);
        complaint.setFiledDate(filedDate);
        complaint = complaintRepository.save(complaint);
        return complaint.getId();
    }

    // Get All service
    public List<Complaint> getAllComplaints() {
        return complaintRepository.getAllComplaint();
    }

    // Get By Id service
    public Complaint getById(Long id) {
        Optional<Complaint> complaint = complaintRepository.findById(id);
        if (complaint.isPresent() && complaint.get().getIsActive()) {
            return complaint.get();
        }
        throw new ResourceNotFoundException(
                "Complaint not found by id: " + id
        );
    }

    // Update service
    public Complaint updatedComplaint(Long id, String updateSubject, String updateDescription, ComplaintStatus updateStatus, Date updateFiledDate) {
        Complaint complaintToUpdate = getById(id);
        complaintToUpdate.setUpdatedDate(new Date());
        complaintToUpdate.setSubject(updateSubject);
        complaintToUpdate.setDescription(updateDescription);
        complaintToUpdate.setStatus(updateStatus);
        complaintToUpdate.setFiledDate(updateFiledDate);
        complaintToUpdate = complaintRepository.save(complaintToUpdate);
        return complaintToUpdate;
    }

    // Delete service
    public Boolean deleteById(Long id) {
        Complaint deleteComplaint = getById(id);
        deleteComplaint.setIsActive(false);
        deleteComplaint.setUpdatedDate(new Date());
        complaintRepository.save(deleteComplaint);
        return true;
    }

    // File complaint business logic
    public Long fileComplaint(Long citizenId, Long operatorId, Long officerId, String subject, String description) {
        // Check citizen
        Citizen citizen = citizenRepository.getById(citizenId);
        if (citizen == null || !citizen.getIsActive()) {
            throw new ResourceNotFoundException(
                    "Citizen not found or inactive"
            );
        }
        // Check operator
        Operator operator = operatorRepository.getById(operatorId);
        if (operator == null || !operator.getIsActive()) {
            throw new ResourceNotFoundException(
                    "Operator not found or inactive"
            );
        }

        // Check officer
        Officer officer = officerRepository.getById(officerId);
        if (officer == null || !officer.getIsActive()) {
            throw new ResourceNotFoundException(
                    "Officer not found or inactive"
            );
        }

        // Create complaint
        Complaint complaint = new Complaint();
        complaint.setCitizen(citizen);
        complaint.setOperator(operator);
        complaint.setOfficer(officer);
        complaint.setSubject(subject);
        complaint.setDescription(description);
        complaint.setFiledDate(new Date());
        complaint.setStatus(ComplaintStatus.OPEN);
        complaint.setIsActive(true);
        complaint.setCreatedDate(new Date());
        complaint = complaintRepository.save(complaint);
        return complaint.getId();
    }

    // Resolve complaint
    public Boolean resolveComplaint(Long complaintId) {
        Complaint complaint = getById(complaintId);
        complaint.setStatus(ComplaintStatus.RESOLVED);
        complaint.setUpdatedDate(new Date());
        complaintRepository.save(complaint);
        return true;
    }

    // Get Open Complaints
    public List<Complaint> getOpenComplaints() {
        return complaintRepository.getOpenComplaints();
    }

    // Get Open Complaints By Operator
    public List<Complaint> getOpenComplaintsByOperator(Long operatorId) {
        Operator operator = operatorRepository.getById(operatorId);
        if (operator == null || !operator.getIsActive()) {
            throw new ResourceNotFoundException(
                    "Operator not found or inactive"
            );
        }
        return complaintRepository.getOpenComplaintsByOperator(operatorId);
    }
}