package com.MOITT.demo.services;

import com.MOITT.demo.entities.Complaint;
import com.MOITT.demo.entities.ComplaintStatus;
import com.MOITT.demo.repositories.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {
    ComplaintRepository complaintRepository;

    @Autowired
    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    //Add service
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

    //Get All service
    public List<Complaint> getAllComplaints() {
        return complaintRepository.getAllComplaints();
    }

    //Get By Id service
    public Complaint getById(Long id) {
        Optional<Complaint> complaint = complaintRepository.findById(id);
        if (complaint.isPresent() && complaint.get().getIsActive()) {
            return complaint.get();
        }

        throw new ResourceNotFoundException(
                "Complaint not found by id: " + id
        );
    }

    //Update service
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

    //Delete service
    public Boolean deleteById(Long id) {
        Complaint deleteComplaint = getById(id);
        deleteComplaint.setIsActive(false);
        deleteComplaint.setUpdatedDate(new Date());
        complaintRepository.save(deleteComplaint);
        return true;
    }
}
