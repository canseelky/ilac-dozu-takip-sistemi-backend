package com.msku.drugdosemonitoringsystem.services;

import com.msku.drugdosemonitoringsystem.controllers.response.DefaultResponse;
import com.msku.drugdosemonitoringsystem.controllers.response.DoctorPatientResponse;
import com.msku.drugdosemonitoringsystem.controllers.response.NotificationUser;
import com.msku.drugdosemonitoringsystem.controllers.response.PatientSearchResponse;
import com.msku.drugdosemonitoringsystem.entities.*;
import java.util.List;
import java.util.UUID;

public interface IDoctorService {

    PatientSearchResponse searchPatient(String phoneNumber, UUID doctorid);
    DefaultResponse<Patient> sendRequestToPatient(UUID patientId, UUID doctorId);
    List<DoctorPatientResponse> seePatients (UUID doctorid);
    List<PatientHistory> seePatientHistory(UUID patientId,UUID doctorId);
    List<Patient> getAll();
    DefaultResponse<Drug>  addDrugToPatient(UUID doctorid, UUID patientid, Long drugid,Integer updateFreq,Integer baseDose);
    Boolean checkEmailInUse(String email);
    Doctor save(Doctor doctor);
    DefaultResponse setNotificationId(String notificationId, UUID doctorId);
    DefaultResponse<Long> getSelectedFormula(UUID doctorId);
    DefaultResponse<Formula> setFormula(Long formulaId,UUID doctorId);
    List<DoctorPatientResponse> getPatientList(UUID doctorId,int pageNumber);
    DefaultResponse<List<NotificationUser>> getNotifications(UUID doctorId);


}
