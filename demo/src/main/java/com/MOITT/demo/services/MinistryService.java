package com.MOITT.demo.services;

import com.MOITT.demo.entities.Ministry;
import com.MOITT.demo.exceptions.ResourceNotFoundException;
import com.MOITT.demo.repositories.MinistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MinistryService {
    MinistryRepository ministryRepository;

    @Autowired
    public MinistryService(MinistryRepository ministryRepository) {
        this.ministryRepository = ministryRepository;
    }

    // Add service
    public Long addMinistry(String name, String address) {
        Ministry ministry = new Ministry();
        ministry.setIsActive(true);
        ministry.setCreatedDate(new Date());
        ministry.setName(name);
        ministry.setAddress(address);
        ministry = ministryRepository.save(ministry);
        return ministry.getId();
    }

    //Get All service
    public List<Ministry> getAllMinistries() {
        return ministryRepository.getAllMinistry();
    }

    //Get By Id service
    public Ministry getById(Long id) {
        Optional<Ministry> ministry = ministryRepository.findById(id);
        if (ministry.isPresent() && ministry.get().getIsActive()) {
            return ministry.get();
        }
        throw new ResourceNotFoundException(
                "Ministry not found by id: " + id
        );
    }

    //Update service
    public Ministry updatedMinistry(Long id, String updateName, String updateAddress) {
        Ministry ministryToUpdate = getById(id);
        ministryToUpdate.setUpdatedDate(new Date());
        ministryToUpdate.setName(updateName);
        ministryToUpdate.setAddress(updateAddress);
        return ministryRepository.save(ministryToUpdate);
    }

    //Delete service
    public Boolean deleteById(Long id) {
        Ministry deleteMinistry = getById(id);
        deleteMinistry.setIsActive(false);
        deleteMinistry.setUpdatedDate(new Date());
        ministryRepository.save(deleteMinistry);
        return true;
    }
}
