package com.MOITT.demo.services;

import com.MOITT.demo.entities.Department;
import com.MOITT.demo.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    //Add service
    public Long addDepartment(String name, String description) {
        Department department = new Department();
        department.setIsActive(true);
        department.setCreatedDate(new Date());
        department.setName(name);
        department.setDescription(description);
        department = departmentRepository.save(department);
        return department.getId();
    }

    //Get All service
    public List<Department> getAllDepartments() {
        return departmentRepository.getAllDeparment();
    }

    //Get By Id
    public Department getById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent() && department.get().getIsActive()) {
            return department.get();
        }
        throw new ResourceNotFoundException(
                "Department not found by id: " + id
        );
    }

    //Update service
    public Department updatedDepartment(Long id, String updateName, String updateDescription) {
        Department departmentToUpdate = getById(id);
        departmentToUpdate.setUpdatedDate(new Date());
        departmentToUpdate.setName(updateName);
        departmentToUpdate.setDescription(updateDescription);
        departmentToUpdate = departmentRepository.save(departmentToUpdate);
        return departmentToUpdate;
    }

    //Delete service
    public Boolean deleteById(Long id) {
        Department deleteDepartment = getById(id);
        deleteDepartment.setIsActive(false);
        deleteDepartment.setUpdatedDate(new Date());
        departmentRepository.save(deleteDepartment);
        return true;
    }
}
