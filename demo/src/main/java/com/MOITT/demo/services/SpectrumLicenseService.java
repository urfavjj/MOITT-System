package com.MOITT.demo.services;

import com.MOITT.demo.entities.LicenseStatus;
import com.MOITT.demo.entities.SpectrumLicense;
import com.MOITT.demo.repositories.SpectrumLicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SpectrumLicenseService {
    SpectrumLicenseRepository spectrumLicenseRepository;

    @Autowired
    public SpectrumLicenseService(SpectrumLicenseRepository spectrumLicenseRepository) {
        this.spectrumLicenseRepository = spectrumLicenseRepository;
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

    //Get All service
    public List<SpectrumLicense> getAllSpectrumLicenses() {
        return spectrumLicenseRepository.getAllSpectrumLicenses();
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
