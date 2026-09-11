package com.MOITT.demo.dto;

import com.MOITT.demo.entities.Project;
import com.MOITT.demo.entities.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private Long projectId;

    @NotBlank(message = "You can't leave the project title to be null")
    @Size(min = 3, max = 200, message = "Project title has to be between 3 to 200 characters")
    private String projectTitle;

    @PositiveOrZero(message = "Budget cannot be negative")
    private Double projectBudget;

    private Date projectStartDate;
    private ProjectStatus projectStatus;

    public static ProjectDTO convertToDTO(Project entity){
        ProjectDTO dto = ProjectDTO.builder()
                .projectId(entity.getId())
                .projectTitle(entity.getTitle())
                .projectBudget(entity.getBudget())
                .projectStartDate(entity.getStartDate())
                .projectStatus(entity.getStatus())
                .build();
        return dto;
    }


    public static List<ProjectDTO> convertToDTO(List<Project> entityList){
        List<ProjectDTO> dtos = new ArrayList<>();
        for(Project project : entityList){
            dtos.add(convertToDTO(project));
        }
        return dtos;
    }
}