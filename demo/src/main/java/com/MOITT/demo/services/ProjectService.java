package com.MOITT.demo.services;

import com.MOITT.demo.entities.Project;
import com.MOITT.demo.entities.ProjectStatus;
import com.MOITT.demo.repositories.MilestoneRepository;
import com.MOITT.demo.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.MOITT.demo.exceptions.ResourceNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    ProjectRepository projectRepository;
    MilestoneRepository milestoneRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, MilestoneRepository milestoneRepository) {
        this.projectRepository = projectRepository;
        this.milestoneRepository = milestoneRepository;
    }


    //Add service
    public Long addProject(String title, Double budget, Date startDate, ProjectStatus status) {
        Project project = new Project();
        project.setIsActive(true);
        project.setCreatedDate(new Date());
        project.setTitle(title);
        project.setBudget(budget);
        project.setStartDate(startDate);
        project.setStatus(status);
        project = projectRepository.save(project);
        return project.getId();
    }


    //Get All service
    public List<Project> getAllProjects() {
        return projectRepository.getAllProject();
    }


    //Get By Id service
    public Project getById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent() && project.get().getIsActive()) {
            return project.get();
        }

        throw new ResourceNotFoundException(
                "Project not found by id: " + id
        );
    }

    // Get projects over budget
    public List<Project> getProjectsOverBudget(Double amount){
        return projectRepository.getProjectsOverBudget(amount);
    }


    // Update service
    public Project updatedProject(Long id, String updateTitle, Double updateBudget, Date updateStartDate, ProjectStatus updateStatus) {
        Project projectToUpdate = getById(id);
        projectToUpdate.setUpdatedDate(new Date());
        projectToUpdate.setTitle(updateTitle);
        projectToUpdate.setBudget(updateBudget);
        projectToUpdate.setStartDate(updateStartDate);
        projectToUpdate.setStatus(updateStatus);
        projectToUpdate = projectRepository.save(projectToUpdate);
        return projectToUpdate;
    }


    // Delete service
    public Boolean deleteById(Long id) {
        Project deleteProject = getById(id);
        deleteProject.setIsActive(false);
        deleteProject.setUpdatedDate(new Date());
        projectRepository.save(deleteProject);
        return true;
    }

    // Get milestone completion percentage
    public Double getMilestoneCompletionPercentage(Long projectId){
        Project project = getById(projectId);
        Long completed = milestoneRepository.countCompletedMilestones(projectId);
        Long total = milestoneRepository.countTotalMilestones(projectId);
        if(total == 0){
            return 0.0;
        }

        return (completed.doubleValue() / total.doubleValue()) * 100;
    }
}
