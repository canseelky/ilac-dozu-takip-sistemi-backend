package com.msku.drugdosemonitoringsystem.repositories;

import com.msku.drugdosemonitoringsystem.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepo extends JpaRepository<Hospital, Long> {
}
