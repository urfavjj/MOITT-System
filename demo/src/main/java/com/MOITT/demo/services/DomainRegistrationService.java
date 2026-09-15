package com.MOITT.demo.services;

import com.MOITT.demo.entities.Citizen;
import com.MOITT.demo.entities.DomainRegistration;
import com.MOITT.demo.entities.DomainStatus;
import com.MOITT.demo.exceptions.BusinessRuleException;
import com.MOITT.demo.repositories.CitizenRepository;
import com.MOITT.demo.repositories.DomainRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.MOITT.demo.exceptions.ResourceNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DomainRegistrationService {
    DomainRegistrationRepository domainRegistrationRepository;
    CitizenRepository citizenRepository;

    @Autowired
    public DomainRegistrationService(DomainRegistrationRepository domainRegistrationRepository, CitizenRepository citizenRepository) {
        this.domainRegistrationRepository = domainRegistrationRepository;
        this.citizenRepository = citizenRepository;
    }

    //Add service
    public Long addDomainRegistration(String domainName, Date registeredDate, Date expiryDate, DomainStatus status) {
        DomainRegistration domainRegistration = new DomainRegistration();
        domainRegistration.setIsActive(true);
        domainRegistration.setCreatedDate(new Date());
        domainRegistration.setDomainName(domainName);
        domainRegistration.setRegisteredDate(registeredDate);
        domainRegistration.setExpiryDate(expiryDate);
        domainRegistration.setStatus(status);
        domainRegistration = domainRegistrationRepository.save(domainRegistration);
        return domainRegistration.getId();
    }

    //Register service
    public Long registerDomain(Long citizenId, String domainName, Date registeredDate, Date expiryDate){
        Citizen citizen = citizenRepository.getById(citizenId);
        if(citizen == null || !citizen.getIsActive()){
            throw new ResourceNotFoundException(
                    "Citizen not found or inactive"
            );
        }

        DomainRegistration existingDomain = domainRegistrationRepository.findActiveDomain(domainName);
        if(existingDomain != null){
            throw new BusinessRuleException(
                    "Domain name already registered"
            );
        }

        DomainRegistration domain = new DomainRegistration();
        domain.setCitizen(citizen);
        domain.setDomainName(domainName);
        domain.setRegisteredDate(registeredDate);
        domain.setExpiryDate(expiryDate);
        domain.setStatus(DomainStatus.ACTIVE);
        domain.setIsActive(true);
        domain.setCreatedDate(new Date());
        domain = domainRegistrationRepository.save(domain);
        return domain.getId();
    }

    //Renew service
    public Boolean renewDomain(Long domainId, Date newExpiryDate) {
        DomainRegistration domain = getById(domainId);
        if (!domain.getStatus().equals(DomainStatus.ACTIVE)) {
            throw new IllegalStateException(
                    "Only active domains can be renewed"
            );
        }
        if (!newExpiryDate.after(domain.getExpiryDate())) {
            throw new IllegalArgumentException(
                    "New expiry date must be after current expiry date"
            );
        }
        domain.setExpiryDate(newExpiryDate);
        domain.setUpdatedDate(new Date());
        domainRegistrationRepository.save(domain);
        return true;
    }

    //Get All service
    public List<DomainRegistration> getAllDomainRegistrations() {
        return domainRegistrationRepository.getAllDomainRegistration();
    }

    //Get By Id service
    public DomainRegistration getById(Long id) {
        Optional<DomainRegistration> domain = domainRegistrationRepository.findById(id);
        if (domain.isPresent() && domain.get().getIsActive()) {
            return domain.get();
        }

        throw new ResourceNotFoundException(
                "Domain Registration not found by id: " + id
        );
    }

    //Update service
    public DomainRegistration updatedDomainRegistration(Long id, String updateDomainName, Date updateRegisteredDate, Date updateExpiryDate, DomainStatus updateStatus) {
        DomainRegistration domainToUpdate = getById(id);
        domainToUpdate.setUpdatedDate(new Date());
        domainToUpdate.setDomainName(updateDomainName);
        domainToUpdate.setRegisteredDate(updateRegisteredDate);
        domainToUpdate.setExpiryDate(updateExpiryDate);
        domainToUpdate.setStatus(updateStatus);
        domainToUpdate = domainRegistrationRepository.save(domainToUpdate);
        return domainToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id) {
        DomainRegistration deleteDomain = getById(id);
        deleteDomain.setIsActive(false);
        deleteDomain.setUpdatedDate(new Date());
        domainRegistrationRepository.save(deleteDomain);
        return true;
    }
}
