package com.MOITT.demo.services;

import com.MOITT.demo.entities.LicenseStatus;
import com.MOITT.demo.entities.Operator;
import com.MOITT.demo.entities.SpectrumLicense;
import com.MOITT.demo.repositories.OperatorRepository;
import com.MOITT.demo.repositories.SpectrumLicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.MOITT.demo.exceptions.ResourceNotFoundException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SpectrumLicenseService {
    SpectrumLicenseRepository spectrumLicenseRepository;
    OperatorRepository operatorRepository;

    @Autowired
    public SpectrumLicenseService(SpectrumLicenseRepository spectrumLicenseRepository, OperatorRepository operatorRepository) {
        this.spectrumLicenseRepository = spectrumLicenseRepository;
        this.operatorRepository = operatorRepository;
    }

    //Add service
    public Long addSpectrumLicense(String bandName, Double frequencyMhz, Date issueDate, Date expiryDate, LicenseStatus status) {
        SpectrumLicense spectrumLicense = new SpectrumLicense();
        spectrumLicense.setIsActive(true);
        spectrumLicense.setCreatedDate(new Date());
        spectrumLicense.setBandName(bandName);
        spectrumLicense.setFrequencyMhz(frequencyMhz);
        spectrumLicense.setIssueDate(issueDate);
        spectrumLicense.setExpiryDate(expiryDate);
        spectrumLicense.setStatus(status);
        spectrumLicense = spectrumLicenseRepository.save(spectrumLicense);
        return spectrumLicense.getId();
    }

    //Issue license
    public Long issueLicense(Long operatorId, String bandName, Double frequencyMhz, Date issueDate, Date expiryDate){
        Operator operator = operatorRepository.getById(operatorId);
        if(operator == null || !operator.getIsActive()){
            throw new ResourceNotFoundException(
                    "Operator not found or inactive"
            );

        }

        if(!expiryDate.after(issueDate)){
            throw new IllegalArgumentException(
                    "Expiry date must be after issue date"
            );

        }

        SpectrumLicense license = new SpectrumLicense();
        license.setIsActive(true);
        license.setCreatedDate(new Date());
        license.setBandName(bandName);
        license.setFrequencyMhz(frequencyMhz);
        license.setIssueDate(issueDate);
        license.setExpiryDate(expiryDate);
        license.setStatus(LicenseStatus.ACTIVE);
        license.setOperator(operator);
        license = spectrumLicenseRepository.save(license);
        return license.getId();
    }

    //Get All service
    public List<SpectrumLicense> getAllSpectrumLicenses() {
        return spectrumLicenseRepository.getAllSpectrumLicense();
    }

    // Get licenses expiring within 30 days
    public List<SpectrumLicense> getLicensesExpiringSoon(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date date = calendar.getTime();
        return spectrumLicenseRepository
                .getExpiringLicenses(date);
    }


    //Get By Id service
    public SpectrumLicense getById(Long id) {
        Optional<SpectrumLicense> license = spectrumLicenseRepository.findById(id);
        if (license.isPresent() && license.get().getIsActive()) {
            return license.get();
        }
        throw new ResourceNotFoundException(
                "Spectrum License not found by id: " + id
        );
    }

    //Update service
    public SpectrumLicense updatedSpectrumLicense(Long id, String updateBandName, Double updateFrequencyMhz, Date updateIssueDate, Date updateExpiryDate, LicenseStatus updateStatus) {
        SpectrumLicense licenseToUpdate = getById(id);
        licenseToUpdate.setUpdatedDate(new Date());
        licenseToUpdate.setBandName(updateBandName);
        licenseToUpdate.setFrequencyMhz(updateFrequencyMhz);
        licenseToUpdate.setIssueDate(updateIssueDate);
        licenseToUpdate.setExpiryDate(updateExpiryDate);
        licenseToUpdate.setStatus(updateStatus);
        licenseToUpdate = spectrumLicenseRepository.save(licenseToUpdate);
        return licenseToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id) {
        SpectrumLicense deleteLicense = getById(id);
        deleteLicense.setIsActive(false);
        deleteLicense.setUpdatedDate(new Date());
        spectrumLicenseRepository.save(deleteLicense);
        return true;
    }
}
