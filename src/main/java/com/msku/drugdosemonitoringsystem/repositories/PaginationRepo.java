package com.msku.drugdosemonitoringsystem.repositories;


import com.msku.drugdosemonitoringsystem.entities.PatientHasDoctor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface PaginationRepo extends   PagingAndSortingRepository<PatientHasDoctor, Long> {
    List<PatientHasDoctor> findPatientHasDoctorByDoctorId(UUID doctorId, Pageable pageable);


}
