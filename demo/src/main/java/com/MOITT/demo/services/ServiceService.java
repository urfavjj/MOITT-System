package com.MOITT.demo.services;

import com.MOITT.demo.repositories.ServiceRepository;
import com.MOITT.demo.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {
    ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    // Add service
    public Long addService(String name, String description, Double fee, Integer processingDays) {
        com.MOITT.demo.entities.Service service = new com.MOITT.demo.entities.Service();
        service.setIsActive(true);
        service.setCreatedDate(new Date());
        service.setName(name);
        service.setDescription(description);
        service.setFee(fee);
        service.setProcessingDays(processingDays);
        service = serviceRepository.save(service);
        return service.getId();
    }

    // Get All service
    public List<com.MOITT.demo.entities.Service> getAllServices() {
        return serviceRepository.getAllService();
    }

    // Get By Id service
    public com.MOITT.demo.entities.Service getById(Long id) {
        Optional<com.MOITT.demo.entities.Service> service = serviceRepository.findById(id);
        if (service.isPresent() && service.get().getIsActive()) {
            return service.get();
        }
        throw new ResourceNotFoundException(
                "Service not found by id: " + id
        );
    }

    // Update service
    public com.MOITT.demo.entities.Service updatedService(Long id, String updateName, String updateDescription, Double updateFee, Integer updateProcessingDays) {
        com.MOITT.demo.entities.Service serviceToUpdate = getById(id);
        serviceToUpdate.setUpdatedDate(new Date());
        serviceToUpdate.setName(updateName);
        serviceToUpdate.setDescription(updateDescription);
        serviceToUpdate.setFee(updateFee);
        serviceToUpdate.setProcessingDays(updateProcessingDays);
        serviceToUpdate = serviceRepository.save(serviceToUpdate);
        return serviceToUpdate;
    }

    // Delete service
    public Boolean deleteById(Long id) {
        com.MOITT.demo.entities.Service deleteService = getById(id);
        deleteService.setIsActive(false);
        deleteService.setUpdatedDate(new Date());
        serviceRepository.save(deleteService);
        return true;
    }
}