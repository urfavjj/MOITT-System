package com.MOITT.demo.controllers;

import com.MOITT.demo.dto.VendorDTO;
import com.MOITT.demo.services.VendorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vendor")
public class VendorController {
    VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    // Add API
    @PostMapping("add")
    public Long addVendor(@Valid @RequestBody VendorDTO dto){
        return vendorService.addVendor(
                dto.getVendorName(),
                dto.getVendorContactEmail(),
                dto.getVendorPhoneNumber(),
                dto.getVendorCountry()
        );
    }


    // Get All API
    @GetMapping("getAll")
    public List<VendorDTO> getAllVendors(){
        return VendorDTO.convertToDTO(vendorService.getAllVendors());
    }



    // Get By Id API
    @GetMapping("getById")
    public VendorDTO getById(@RequestParam Long id){
        return VendorDTO.convertToDTO(vendorService.getById(id));
    }



    // Update API
    @PutMapping("update")
    public VendorDTO updateVendor(@Valid @RequestBody VendorDTO dto){
        return VendorDTO.convertToDTO(
                vendorService.updatedVendor(
                        dto.getVendorId(),
                        dto.getVendorName(),
                        dto.getVendorContactEmail(),
                        dto.getVendorPhoneNumber(),
                        dto.getVendorCountry()
                )
        );
    }



    // Delete API
    @DeleteMapping("delete")
    public Boolean deleteVendorById(@RequestParam Long id){
        return vendorService.deleteById(id);
    }
}