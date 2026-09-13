package com.MOITT.demo.repositories;

import com.MOITT.demo.entities.Inspection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspectionRepository extends JpaRepository<Inspection, Long> {
    // Get all active inspections
    @Query("SELECT i FROM Inspection i WHERE i.isActive=true")
    List<Inspection> getAllInspection();



    // Get active inspection by id
    @Query("SELECT i FROM Inspection i WHERE i.isActive=true AND i.id=:inspection")
    Inspection getById(@Param("inspection") Long id);
}
