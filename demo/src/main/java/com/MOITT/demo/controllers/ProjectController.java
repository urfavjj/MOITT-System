package com.MOITT.demo.controllers;

import com.MOITT.demo.dto.ProjectDTO;
import com.MOITT.demo.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("project")
public class ProjectController {
    ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Add API
    @PostMapping("add")
    public Long addProject(@Valid @RequestBody ProjectDTO dto){
        return projectService.addProject(
                dto.getProjectTitle(),
                dto.getProjectBudget(),
                dto.getProjectStartDate(),
                dto.getProjectStatus()
        );
    }


    // Get All API
    @GetMapping("getAll")
    public List<ProjectDTO> getAllProjects(){
        return ProjectDTO.convertToDTO(projectService.getAllProjects());
    }


    // Get By Id API
    @GetMapping("getById")
    public ProjectDTO getById(@RequestParam Long id){
        return ProjectDTO.convertToDTO(projectService.getById(id));
    }


    //Project API
    @GetMapping("/{id}/completion")
    public Double completion(@PathVariable Long id){

        return projectService.getMilestoneCompletionPercentage(id);

    }

    // Update API
    @PutMapping("update")
    public ProjectDTO updateProject(@Valid @RequestBody ProjectDTO dto){
        return ProjectDTO.convertToDTO(
                projectService.updatedProject(
                        dto.getProjectId(),
                        dto.getProjectTitle(),
                        dto.getProjectBudget(),
                        dto.getProjectStartDate(),
                        dto.getProjectStatus()
                )
        );
    }

    //over-budget API
    @GetMapping("overBudget")
    public List<ProjectDTO> getProjectsOverBudget(@RequestParam Double amount){
        return ProjectDTO.convertToDTO(
                projectService.getProjectsOverBudget(amount)
        );
    }


    // Delete API
    @DeleteMapping("delete")
    public Boolean deleteProjectById(@RequestParam Long id){
        return projectService.deleteById(id);
    }
}