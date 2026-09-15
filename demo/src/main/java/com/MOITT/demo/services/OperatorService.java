package com.MOITT.demo.services;

import com.MOITT.demo.entities.Operator;
import com.MOITT.demo.repositories.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.MOITT.demo.exceptions.ResourceNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OperatorService {
    OperatorRepository operatorRepository;

    @Autowired
    public OperatorService(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    //Add service
    public Long addOperator(String name, String licenseNumber, String contactEmail, String country) {
        Operator operator = new Operator();
        operator.setIsActive(true);
        operator.setCreatedDate(new Date());
        operator.setName(name);
        operator.setLicenseNumber(licenseNumber);
        operator.setContactEmail(contactEmail);
        operator.setCountry(country);
        operator = operatorRepository.save(operator);
        return operator.getId();
    }

    //Get All service
    public List<Operator> getAllOperators() {
        return operatorRepository.getAllOperator();
    }

    //Get By Id service
    public Operator getById(Long id) {
        Optional<Operator> operator = operatorRepository.findById(id);
        if (operator.isPresent() && operator.get().getIsActive()) {
            return operator.get();
        }
        throw new ResourceNotFoundException(
                "Operator not found by id: " + id
        );
    }

    //Update service
    public Operator updatedOperator(Long id, String updateName, String updateLicenseNumber, String updateContactEmail, String updateCountry) {
        Operator operatorToUpdate = getById(id);
        operatorToUpdate.setUpdatedDate(new Date());
        operatorToUpdate.setName(updateName);
        operatorToUpdate.setLicenseNumber(updateLicenseNumber);
        operatorToUpdate.setContactEmail(updateContactEmail);
        operatorToUpdate.setCountry(updateCountry);
        operatorToUpdate = operatorRepository.save(operatorToUpdate);
        return operatorToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id) {
        Operator deleteOperator = getById(id);
        deleteOperator.setIsActive(false);
        deleteOperator.setUpdatedDate(new Date());
        operatorRepository.save(deleteOperator);
        return true;
    }

    // Count active licenses
    public Long getActiveLicensesCount(Long operatorId){
        return operatorRepository.countActiveLicenses(operatorId);
    }

    // Count open complaints
    public Long getOpenComplaintsCount(Long operatorId){
        return operatorRepository.countOpenComplaints(operatorId);
    }

}
