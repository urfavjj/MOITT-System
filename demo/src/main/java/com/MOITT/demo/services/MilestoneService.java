package com.MOITT.demo.services;

import com.MOITT.demo.entities.Milestone;
import com.MOITT.demo.entities.MilestoneStatus;
import com.MOITT.demo.entities.Project;
import com.MOITT.demo.repositories.MilestoneRepository;
import com.MOITT.demo.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.MOITT.demo.exceptions.ResourceNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MilestoneService {
    MilestoneRepository milestoneRepository;
    ProjectRepository projectRepository;

    @Autowired
    public MilestoneService(MilestoneRepository milestoneRepository, ProjectRepository projectRepository){
        this.milestoneRepository = milestoneRepository;
        this.projectRepository = projectRepository;
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

    // Add milestone to project
    public Long addProjectMilestone(Long projectId, String title, Date dueDate){
        Project project = projectRepository.getById(projectId);
        if(project == null || !project.getIsActive()){
            throw new ResourceNotFoundException(
                    "Project not found or inactive"
            );
        }

        Milestone milestone = new Milestone();
        milestone.setProject(project);
        milestone.setTitle(title);
        milestone.setDueDate(dueDate);
        milestone.setStatus(MilestoneStatus.PENDING);
        milestone.setIsActive(true);
        milestone.setCreatedDate(new Date());
        milestone = milestoneRepository.save(milestone);
        return milestone.getId();
    }


    //Get All service
    public List<Milestone> getAllMilestones() {
        return milestoneRepository.getAllMilestone();
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

    // Mark milestone complete
    public Boolean completeMilestone(Long milestoneId){
        Milestone milestone = getById(milestoneId);
        milestone.setStatus(MilestoneStatus.COMPLETED);
        milestone.setUpdatedDate(new Date());
        milestoneRepository.save(milestone);
        return true;
    }
}
