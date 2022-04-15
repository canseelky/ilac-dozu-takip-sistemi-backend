package com.msku.drugdosemonitoringsystem.repositories;

import com.msku.drugdosemonitoringsystem.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, UUID> {
    Doctor findDoctorByEmail(String email);

    Doctor getById(UUID id);
}
