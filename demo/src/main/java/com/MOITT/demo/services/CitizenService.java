package com.MOITT.demo.services;

import com.MOITT.demo.entities.Citizen;
import com.MOITT.demo.exceptions.ResourceNotFoundException;
import com.MOITT.demo.repositories.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CitizenService {
    CitizenRepository citizenRepository;

    @Autowired
    public CitizenService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    //Add service
    public Long addCitizen(String name, String nationalId, String phoneNumber, String email) {
        Citizen citizen = new Citizen();
        citizen.setIsActive(true);
        citizen.setCreatedDate(new Date());
        citizen.setName(name);
        citizen.setNationalId(nationalId);
        citizen.setPhoneNumber(phoneNumber);
        citizen.setEmail(email);
        citizen = citizenRepository.save(citizen);
        return citizen.getId();
    }

    //Get All service
    public List<Citizen> getAllCitizens() {
        return citizenRepository.getAllCitizen();
    }

    //Get By Id service
    public Citizen getById(Long id) {
        Optional<Citizen> citizen = citizenRepository.findById(id);
        if (citizen.isPresent() && citizen.get().getIsActive()) {
            return citizen.get();
        }
        throw new ResourceNotFoundException(
                "Citizen not found by id: " + id
        );
    }

    //Update service
    public Citizen updatedCitizen(Long id, String updateName, String updateNationalId, String updatePhoneNumber, String updateEmail) {
        Citizen citizenToUpdate = getById(id);
        citizenToUpdate.setUpdatedDate(new Date());
        citizenToUpdate.setName(updateName);
        citizenToUpdate.setNationalId(updateNationalId);
        citizenToUpdate.setPhoneNumber(updatePhoneNumber);
        citizenToUpdate.setEmail(updateEmail);
        citizenToUpdate = citizenRepository.save(citizenToUpdate);
        return citizenToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id) {
        Citizen deleteCitizen = getById(id);
        deleteCitizen.setIsActive(false);
        deleteCitizen.setUpdatedDate(new Date());
        citizenRepository.save(deleteCitizen);
        return true;
    }
}
