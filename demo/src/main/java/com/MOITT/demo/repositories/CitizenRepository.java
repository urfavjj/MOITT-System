package com.MOITT.demo.repositories;

import com.MOITT.demo.entities.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
    // Get all active citizens
    @Query("SELECT c FROM Citizen c WHERE c.isActive=true")
    List<Citizen> getAllCitizen();



    // Get active citizen by id
    @Query("SELECT c FROM Citizen c WHERE c.isActive=true AND c.id=:citizen")
    Citizen getById(@Param("citizen") Long id);
}
