package com.MOITT.demo.repositories;

import com.MOITT.demo.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Get all active projects
    @Query("SELECT p FROM Project p WHERE p.isActive=true")
    List<Project> getAllProject();



    // Get active project by id
    @Query("SELECT p FROM Project p WHERE p.isActive=true AND p.id=:project")
    Project getById(@Param("project") Long id);
}
