package com.MOITT.demo.services;

import com.MOITT.demo.entities.Application;
import com.MOITT.demo.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    //Add service
    public Long addApplication(Application application) {
        application.setIsActive(true);
        application.setCreatedDate(new Date());
        application = applicationRepository.save(application);
        return application.getId();
    }

    //Get All service
    public List<Application> getAllApplications() {
        return applicationRepository.getAllApplication();
    }

    //Get By Id service
    public Application getById(Long id) {
        Optional<Application> application = applicationRepository.findById(id);
        if (application.isPresent()
                && application.get().getIsActive()) {

            return application.get();
        }
        throw new ResourceNotFoundException(
                "Application not found by id: " + id
        );
    }

    //Update Service
    public Application updatedApplication(Long id, Application updatedApplication) {
        Application applicationToUpdate = getById(id);
        applicationToUpdate.setUpdatedDate(new Date());
        applicationToUpdate.setApplicationDate(updatedApplication.getApplicationDate());
        applicationToUpdate.setStatus(updatedApplication.getStatus());
        applicationToUpdate.setReferenceNumber(updatedApplication.getReferenceNumber());
        applicationToUpdate = applicationRepository.save(applicationToUpdate);
        return applicationToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id) {
        Application deleteApplication = getById(id);
        deleteApplication.setIsActive(false);
        deleteApplication.setUpdatedDate(new Date());
        applicationRepository.save(deleteApplication);
        return true;
    }
}
