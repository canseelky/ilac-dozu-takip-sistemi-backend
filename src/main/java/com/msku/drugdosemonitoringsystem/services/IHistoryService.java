package com.msku.drugdosemonitoringsystem.services;

import com.msku.drugdosemonitoringsystem.entities.PatientHistory;
import java.util.List;
import java.util.UUID;

public interface IHistoryService {
    List<PatientHistory> getHistory(UUID patientId);

}
