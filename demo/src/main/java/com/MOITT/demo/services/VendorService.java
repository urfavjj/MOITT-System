package com.MOITT.demo.services;

import com.MOITT.demo.entities.Vendor;
import com.MOITT.demo.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VendorService {
    VendorRepository vendorRepository;

    @Autowired
    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }


    //Add service
    public Long addVendor(String name, String contactEmail, String phoneNumber, String country) {
        Vendor vendor = new Vendor();
        vendor.setIsActive(true);
        vendor.setCreatedDate(new Date());
        vendor.setName(name);
        vendor.setContactEmail(contactEmail);
        vendor.setPhoneNumber(phoneNumber);
        vendor.setCountry(country);
        vendor = vendorRepository.save(vendor);
        return vendor.getId();
    }


    //Get All service
    public List<Vendor> getAllVendors() {
        return vendorRepository.getAllVendors();
    }


    //Get By Id service
    public Vendor getById(Long id) {
        Optional<Vendor> vendor = vendorRepository.findById(id);
        if (vendor.isPresent() && vendor.get().getIsActive()) {
            return vendor.get();
        }
        throw new ResourceNotFoundException(
                "Vendor not found by id: " + id
        );
    }

    // Update service
    public Vendor updatedVendor(Long id, String updateName, String updateContactEmail, String updatePhoneNumber, String updateCountry) {
        Vendor vendorToUpdate = getById(id);
        vendorToUpdate.setUpdatedDate(new Date());
        vendorToUpdate.setName(updateName);
        vendorToUpdate.setContactEmail(updateContactEmail);
        vendorToUpdate.setPhoneNumber(updatePhoneNumber);
        vendorToUpdate.setCountry(updateCountry);
        vendorToUpdate = vendorRepository.save(vendorToUpdate);
        return vendorToUpdate;
    }


    //Delete service
    public Boolean deleteById(Long id) {
        Vendor deleteVendor = getById(id);
        deleteVendor.setIsActive(false);
        deleteVendor.setUpdatedDate(new Date());
        vendorRepository.save(deleteVendor);
        return true;
    }
}
