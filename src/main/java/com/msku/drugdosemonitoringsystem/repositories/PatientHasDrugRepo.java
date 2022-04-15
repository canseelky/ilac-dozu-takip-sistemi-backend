package com.msku.drugdosemonitoringsystem.repositories;

import com.msku.drugdosemonitoringsystem.entities.PatientHasDrug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface PatientHasDrugRepo extends JpaRepository<PatientHasDrug,Long> {
    PatientHasDrug findByPatientIdAndDrugId(UUID patientId, Long drugId);
    List<PatientHasDrug> findAllByPatientId(UUID patientId);
    PatientHasDrug findPatientHasDrugById(Long id);
}
