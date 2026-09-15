package com.MOITT.demo.repositories;

import com.MOITT.demo.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // Get all active departments
    @Query("SELECT d FROM Department d WHERE d.isActive=true")
    List<Department> getAllDepartment();



    // Get active department by id
    @Query("SELECT d FROM Department d WHERE d.isActive=true AND d.id=:department")
    Department getById(@Param("department") Long id);

    // Count pending applications per department
    @Query("SELECT COUNT(a) FROM Application a WHERE a.isActive=true AND a.status='PENDING' AND a.officer.department.id=:departmentId")
    Long countPendingApplications(@Param("departmentId") Long departmentId);

    // Count officer workload
    @Query("SELECT COUNT(a) FROM Application a WHERE a.isActive=true AND a.officer.department.id=:departmentId")
    Long countOfficerWorkload(@Param("departmentId") Long departmentId);
}
