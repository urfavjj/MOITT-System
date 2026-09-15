package com.MOITT.demo.controllers;

import com.MOITT.demo.dto.ServiceDTO;
import com.MOITT.demo.services.ServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("service")
public class ServiceController {
    ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // Add API
    @PostMapping("add")
    public Long addService(@Valid @RequestBody ServiceDTO dto){

        return serviceService.addService(
                dto.getServiceName(),
                dto.getServiceDescription(),
                dto.getServiceFee(),
                dto.getServiceProcessingDays()
        );
    }


    // Get All API
    @GetMapping("getAll")
    public List<ServiceDTO> getAllServices(){
        return ServiceDTO.convertToDTO(serviceService.getAllServices());
    }



    // Get By Id API
    @GetMapping("getById")
    public ServiceDTO getById(@RequestParam Long id){
        return ServiceDTO.convertToDTO(serviceService.getById(id));
    }



    // Update API
    @PutMapping("update")
    public ServiceDTO updateService(@Valid @RequestBody ServiceDTO dto){
        return ServiceDTO.convertToDTO(serviceService.updatedService(
                        dto.getServiceId(),
                        dto.getServiceName(),
                        dto.getServiceDescription(),
                        dto.getServiceFee(),
                        dto.getServiceProcessingDays())
        );
    }



    // Delete API
    @DeleteMapping("delete")
    public Boolean deleteServiceById(@RequestParam Long id){
        return serviceService.deleteById(id);
    }
}