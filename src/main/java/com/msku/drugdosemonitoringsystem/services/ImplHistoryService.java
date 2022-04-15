package com.msku.drugdosemonitoringsystem.services;

import com.msku.drugdosemonitoringsystem.entities.Patient;
import com.msku.drugdosemonitoringsystem.entities.PatientHistory;
import com.msku.drugdosemonitoringsystem.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ImplHistoryService implements IHistoryService{

    @Autowired
    PatientRepo patientRepo;

    @Override
    public List<PatientHistory> getHistory(UUID patientId) {
        Patient patient = patientRepo.getById(patientId);
        List<PatientHistory> history = patient.getHistoryPatient();
        return history;
    }

}
