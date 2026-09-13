package com.MOITT.demo.repositories;

import com.MOITT.demo.entities.Officer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficerRepository extends JpaRepository<Officer, Long> {
    // Get all active officers
    @Query("SELECT o FROM Officer o WHERE o.isActive=true")
    List<Officer> getAllOfficer();



    // Get active officer by id
    @Query("SELECT o FROM Officer o WHERE o.isActive=true AND o.id=:officer")
    Officer getById(@Param("officer") Long id);
}
