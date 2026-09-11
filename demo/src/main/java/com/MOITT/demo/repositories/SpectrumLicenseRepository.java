package com.MOITT.demo.repositories;

import com.MOITT.demo.entities.SpectrumLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpectrumLicenseRepository extends JpaRepository<SpectrumLicense, Long> {
}
