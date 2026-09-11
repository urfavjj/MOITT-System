package com.MOITT.demo.services;

import com.MOITT.demo.entities.DomainRegistration;
import com.MOITT.demo.entities.DomainStatus;
import com.MOITT.demo.repositories.DomainRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DomainRegistrationService {
    DomainRegistrationRepository domainRegistrationRepository;

    @Autowired
    public DomainRegistrationService(DomainRegistrationRepository domainRegistrationRepository) {
        this.domainRegistrationRepository = domainRegistrationRepository;
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

    //Get All service
    public List<DomainRegistration> getAllDomainRegistrations() {
        return domainRegistrationRepository.getAllDomainRegistrations();
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
