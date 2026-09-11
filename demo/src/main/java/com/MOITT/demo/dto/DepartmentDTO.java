package com.MOITT.demo.dto;

import com.MOITT.demo.entities.Department;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private Long departmentId;

    @NotBlank(message = "You can't leave the department name to be null")
    @Size(min = 3, max = 100, message = "Department name has to be between 3 to 100 characters")
    private String departmentName;

    @Size(max = 500, message = "Department description cannot exceed 500 characters")
    private String departmentDescription;

    public static DepartmentDTO convertToDTO(Department entity){
        DepartmentDTO dto = DepartmentDTO.builder()
                .departmentId(entity.getId())
                .departmentName(entity.getName())
                .departmentDescription(entity.getDescription())
                .build();
        return dto;
    }

    public static List<DepartmentDTO> convertToDTO(List<Department> entityList){
        List<DepartmentDTO> dtos = new ArrayList<>();
        for(Department department : entityList){
            dtos.add(convertToDTO(department));
        }
        return dtos;
    }
}
