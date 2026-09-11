package com.MOITT.demo.repositories;

import com.MOITT.demo.entities.Ministry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinistryRepository extends JpaRepository<Ministry, Long> {
}
