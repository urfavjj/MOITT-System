package com.MOITT.demo.services;

import com.MOITT.demo.entities.Inspection;
import com.MOITT.demo.repositories.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InspectionService {
    InspectionRepository inspectionRepository;

    @Autowired
    public InspectionService(
            InspectionRepository inspectionRepository
    ) {
        this.inspectionRepository = inspectionRepository;
    }


    //Add service
    public Long addInspection(Date inspectionDate, String result, String notes) {
        Inspection inspection = new Inspection();
        inspection.setIsActive(true);
        inspection.setCreatedDate(new Date());
        inspection.setInspectionDate(inspectionDate);
        inspection.setResult(result);
        inspection.setNotes(notes);
        inspection = inspectionRepository.save(inspection);
        return inspection.getId();
    }


    //Get All service
    public List<Inspection> getAllInspections() {
        return inspectionRepository.getAllInspections();
    }


    //Get By Id service
    public Inspection getById(Long id) {
        Optional<Inspection> inspection = inspectionRepository.findById(id);
        if (inspection.isPresent() && inspection.get().getIsActive()) {
            return inspection.get();
        }
        throw new ResourceNotFoundException(
                "Inspection not found by id: " + id
        );
    }


    //Update service
    public Inspection updatedInspection(Long id, Date updateInspectionDate, String updateResult, String updateNotes) {
        Inspection inspectionToUpdate = getById(id);
        inspectionToUpdate.setUpdatedDate(new Date());
        inspectionToUpdate.setInspectionDate(updateInspectionDate);
        inspectionToUpdate.setResult(updateResult);
        inspectionToUpdate.setNotes(updateNotes);
        inspectionToUpdate = inspectionRepository.save(inspectionToUpdate);
        return inspectionToUpdate;
    }


    //Delete service
    public Boolean deleteById(Long id) {
        Inspection deleteInspection = getById(id);
        deleteInspection.setIsActive(false);
        deleteInspection.setUpdatedDate(new Date());
        inspectionRepository.save(deleteInspection);
        return true;
    }
}
