package com.MOITT.demo.services;

import com.MOITT.demo.entities.Officer;
import com.MOITT.demo.repositories.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OfficerService {
    OfficerRepository officerRepository;

    @Autowired
    public OfficerService(OfficerRepository officerRepository) {
        this.officerRepository = officerRepository;
    }

    //Add service
    public Long addOfficer(String name, String email, String phoneNumber, String designation) {
        Officer officer = new Officer();
        officer.setIsActive(true);
        officer.setCreatedDate(new Date());
        officer.setName(name);
        officer.setEmail(email);
        officer.setPhoneNumber(phoneNumber);
        officer.setDesignation(designation);
        officer = officerRepository.save(officer);
        return officer.getId();
    }

    //Get All service
    public List<Officer> getAllOfficers() {
        return officerRepository.getAllOfficer();
    }

    //Get By Id service
    public Officer getById(Long id) {
        Optional<Officer> officer = officerRepository.findById(id);
        if (officer.isPresent() && officer.get().getIsActive()) {
            return officer.get();
        }
        throw new ResourceNotFoundException(
                "Officer not found by id: " + id
        );
    }


    //Update service
    public Officer updatedOfficer(Long id, String updateName, String updateEmail, String updatePhoneNumber, String updateDesignation) {
        Officer officerToUpdate = getById(id);
        officerToUpdate.setUpdatedDate(new Date());
        officerToUpdate.setName(updateName);
        officerToUpdate.setEmail(updateEmail);
        officerToUpdate.setPhoneNumber(updatePhoneNumber);
        officerToUpdate.setDesignation(updateDesignation);
        officerToUpdate = officerRepository.save(officerToUpdate);
        return officerToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id) {
        Officer deleteOfficer = getById(id);
        deleteOfficer.setIsActive(false);
        deleteOfficer.setUpdatedDate(new Date());
        officerRepository.save(deleteOfficer);
        return true;
    }
}
