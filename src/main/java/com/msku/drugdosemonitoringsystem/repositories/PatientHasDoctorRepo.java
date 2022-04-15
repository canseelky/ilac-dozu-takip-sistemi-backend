package com.msku.drugdosemonitoringsystem.repositories;

import com.msku.drugdosemonitoringsystem.entities.PatientHasDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PatientHasDoctorRepo extends JpaRepository<PatientHasDoctor,Long> {
    List<PatientHasDoctor> findPatientHasDoctorByDoctorId(UUID doctorId);
    PatientHasDoctor findPatientHasDoctorByPatientIdAndDoctorId(UUID patientId,UUID doctorId);
    List<PatientHasDoctor> findPatientHasDoctorByPatientId(UUID patientId);



}
