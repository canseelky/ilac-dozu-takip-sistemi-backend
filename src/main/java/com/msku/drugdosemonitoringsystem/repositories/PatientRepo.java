package com.msku.drugdosemonitoringsystem.repositories;

import com.msku.drugdosemonitoringsystem.entities.Patient;
import com.msku.drugdosemonitoringsystem.entities.PatientHasDrug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PatientRepo extends JpaRepository<Patient,UUID> {

    List<PatientHasDrug>  getPatientHasDrugsById(UUID id);

    Patient findPatientByPhoneNumber(String number);
    Patient findPatientByName(String userName);
    Patient findPatientByEmail(String email);

    Patient getById(UUID id);

}
