package com.MOITT.demo.services;

import com.MOITT.demo.entities.Milestone;
import com.MOITT.demo.entities.MilestoneStatus;
import com.MOITT.demo.repositories.MilestoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MilestoneService {
    MilestoneRepository milestoneRepository;

    @Autowired
    public MilestoneService(MilestoneRepository milestoneRepository) {
        this.milestoneRepository = milestoneRepository;
    }

    //Add service
    public Long addMilestone(String title, Date dueDate, MilestoneStatus status) {
        Milestone milestone = new Milestone();
        milestone.setIsActive(true);
        milestone.setCreatedDate(new Date());
        milestone.setTitle(title);
        milestone.setDueDate(dueDate);
        milestone.setStatus(status);
        milestone = milestoneRepository.save(milestone);
        return milestone.getId();
    }


    //Get All service
    public List<Milestone> getAllMilestones() {
        return milestoneRepository.getAllMilestones();
    }


    //Get By Id service
    public Milestone getById(Long id) {
        Optional<Milestone> milestone = milestoneRepository.findById(id);
        if (milestone.isPresent() && milestone.get().getIsActive()) {
            return milestone.get();
        }

        throw new ResourceNotFoundException(
                "Milestone not found by id: " + id
        );
    }

    //Update service
    public Milestone updatedMilestone(Long id, String updateTitle, Date updateDueDate, MilestoneStatus updateStatus) {
        Milestone milestoneToUpdate = getById(id);
        milestoneToUpdate.setUpdatedDate(new Date());
        milestoneToUpdate.setTitle(updateTitle);
        milestoneToUpdate.setDueDate(updateDueDate);
        milestoneToUpdate.setStatus(updateStatus);
        milestoneToUpdate = milestoneRepository.save(milestoneToUpdate);
        return milestoneToUpdate;
    }


    //Delete service
    public Boolean deleteById(Long id) {
        Milestone deleteMilestone = getById(id);
        deleteMilestone.setIsActive(false);
        deleteMilestone.setUpdatedDate(new Date());
        milestoneRepository.save(deleteMilestone);
        return true;
    }
}
