package com.MOITT.demo.repositories;

import com.MOITT.demo.entities.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    // Get all active milestones
    @Query("SELECT m FROM Milestone m WHERE m.isActive=true")
    List<Milestone> getAllMilestone();

    // Get active milestone by id
    @Query("SELECT m FROM Milestone m WHERE m.isActive=true AND m.id=:milestone")
    Milestone getById(@Param("milestone") Long id);

    @Query("SELECT COUNT(m) FROM Milestone m WHERE m.isActive=true AND m.project.id=:projectId AND m.status='COMPLETED'")
    Long countCompletedMilestones(@Param("projectId") Long projectId);

    @Query("SELECT COUNT(m) FROM Milestone m WHERE m.isActive=true AND m.project.id=:projectId")
    Long countTotalMilestones(@Param("projectId") Long projectId);
}
