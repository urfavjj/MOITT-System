package com.MOITT.demo.repositories;

import com.MOITT.demo.entities.Ministry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MinistryRepository extends JpaRepository<Ministry, Long> {
    // Get all active ministries
    @Query("SELECT m FROM Ministry m WHERE m.isActive=true")
    List<Ministry> getAllMinistry();



    // Get active ministry by id
    @Query("SELECT m FROM Ministry m WHERE m.isActive=true AND m.id=:ministry")
    Ministry getById(@Param("ministry") Long id);
}
