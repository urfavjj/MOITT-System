package com.MOITT.demo.repositories;

import com.MOITT.demo.entities.SpectrumLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpectrumLicenseRepository extends JpaRepository<SpectrumLicense, Long> {
    // Get all active spectrum licenses
    @Query("SELECT s FROM SpectrumLicense s WHERE s.isActive=true")
    List<SpectrumLicense> getAllSpectrumLicense();



    // Get active spectrum license by id
    @Query("SELECT s FROM SpectrumLicense s WHERE s.isActive=true AND s.id=:SpectrumLicense")
    SpectrumLicense getById(@Param("SpectrumLicense") Long id);
}
