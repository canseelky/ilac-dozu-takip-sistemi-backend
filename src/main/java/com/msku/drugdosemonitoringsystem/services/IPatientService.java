package com.msku.drugdosemonitoringsystem.services;

import com.msku.drugdosemonitoringsystem.controllers.response.DefaultResponse;
import com.msku.drugdosemonitoringsystem.controllers.response.HistoryResponse;
import com.msku.drugdosemonitoringsystem.controllers.response.NotificationUser;
import com.msku.drugdosemonitoringsystem.entities.Patient;
import java.util.List;
import java.util.UUID;

public interface IPatientService {

    DefaultResponse updateData(Double weight, Double height, UUID patientId);
    void updateHeight(Double height, UUID patientId);
    DefaultResponse<Patient>  personalInfo(UUID patientId);
    void resetPassword(String oldPassword, String newPassword);
    List<Patient> getAll();
    DefaultResponse setNotificationId(String notificationId, UUID patientId);
    Patient getPatientByEmail(String email);
    DefaultResponse<List<HistoryResponse>> history(UUID patientId);
    Patient savePatient(Patient patient);
    DefaultResponse getDoctors(UUID patientId);
    DefaultResponse getDoctor(UUID doctor);
    DefaultResponse<List<NotificationUser>>  getNotifications(UUID patientId);


}
