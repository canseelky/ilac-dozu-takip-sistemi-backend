package com.msku.drugdosemonitoringsystem.repositories;

import com.msku.drugdosemonitoringsystem.entities.Patient;
import com.msku.drugdosemonitoringsystem.entities.PatientHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface HistoryRepo extends JpaRepository<PatientHistory,Long> {
    List<PatientHistory> getPatientHistoryByPatient(Patient patient);
}
