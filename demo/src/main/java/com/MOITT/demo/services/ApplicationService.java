package com.MOITT.demo.services;

import com.MOITT.demo.entities.*;
import com.MOITT.demo.repositories.*;
import com.MOITT.demo.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApplicationService {
    ApplicationRepository applicationRepository;
    CitizenRepository citizenRepository;
    ServiceRepository serviceRepository;
    OfficerRepository officerRepository;
    DocumentRepository documentRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, CitizenRepository citizenRepository, ServiceRepository serviceRepository, OfficerRepository officerRepository, DocumentRepository documentRepository) {
        this.applicationRepository = applicationRepository;
        this.citizenRepository = citizenRepository;
        this.serviceRepository = serviceRepository;
        this.officerRepository = officerRepository;
        this.documentRepository = documentRepository;
    }

    //Add service
    public Long addApplication(Application application) {
        application.setIsActive(true);
        application.setCreatedDate(new Date());
        application = applicationRepository.save(application);
        return application.getId();
    }

    // Submit application
    public Long submitApplication(Long citizenId, Long serviceId, Long officerId){
        Citizen citizen = citizenRepository.getById(citizenId);
        if(citizen == null || !citizen.getIsActive()){
            throw new ResourceNotFoundException(
                    "Citizen not found or inactive"
            );
        }
        com.MOITT.demo.entities.Service service =
                serviceRepository.getById(serviceId);
        if(service == null || !service.getIsActive()){
            throw new ResourceNotFoundException(
                    "Service not found or inactive"
            );
        }
        Officer officer = officerRepository.getById(officerId);
        if(officer == null || !officer.getIsActive()){
            throw new ResourceNotFoundException(
                    "Officer not found or inactive"
            );
        }
        Application application = new Application();
        application.setCitizen(citizen);
        application.setService(service);
        application.setOfficer(officer);
        application.setReferenceNumber(
                "APP-" + UUID.randomUUID()
        );
        application.setStatus(ApplicationStatus.PENDING);
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

    // Get applications by status
    public List<Application> getApplicationsByStatus(ApplicationStatus status){
        return applicationRepository.getApplicationsByStatus(status);
    }

    // Get citizen application history
    public List<Application> getCitizenApplicationHistory(Long citizenId){
        Citizen citizen = citizenRepository.getById(citizenId);
        if(citizen == null || !citizen.getIsActive()){
            throw new ResourceNotFoundException(
                    "Citizen not found or inactive"
            );
        }
        return applicationRepository.getCitizenApplicationHistory(citizenId);
    }

    // Approve or reject application
    public Boolean processApplicationDecision(Long applicationId, Long officerId, String decision, String documentTitle){
        Application application = getById(applicationId);
        Officer officer = officerRepository.getById(officerId);
        if(officer == null || !officer.getIsActive()){
            throw new ResourceNotFoundException(
                    "Officer not found or inactive"
            );
        }

        application.setOfficer(officer);
        if(decision.equalsIgnoreCase("APPROVED")){
            application.setStatus(ApplicationStatus.APPROVED);
        }
        else if(decision.equalsIgnoreCase("REJECTED")){
            application.setStatus(ApplicationStatus.REJECTED);

        }
        else{
            throw new IllegalArgumentException(
                    "Invalid decision"
            );
        }

        application.setUpdatedDate(new Date());
        applicationRepository.save(application);
        Document document = new Document();
        document.setTitle(documentTitle);
        document.setType("DECISION");
        document.setUploadDate(new Date());
        document.setApplication(application);
        document.setIsActive(true);
        documentRepository.save(document);
        return true;
    }
}
