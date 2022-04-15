package com.msku.drugdosemonitoringsystem.services;

import com.msku.drugdosemonitoringsystem.controllers.response.DefaultResponse;
import com.msku.drugdosemonitoringsystem.entities.PatientHasDoctor;
import com.msku.drugdosemonitoringsystem.enums.ResponseTypes;
import com.msku.drugdosemonitoringsystem.repositories.PatientHasDoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;


@Service
public class ImplSharePermissionService implements ISharePermissionService {

    @Autowired
    private PatientHasDoctorRepo mPatientHasDoctorRepo;


    @Override
    public DefaultResponse givePermissionToDoctor(UUID doctorId, UUID patientId) {
       PatientHasDoctor patientDoctor = mPatientHasDoctorRepo.findPatientHasDoctorByPatientIdAndDoctorId(patientId, doctorId);
       try{
           patientDoctor.setIsApproved(1);
           mPatientHasDoctorRepo.save(patientDoctor);
           return new DefaultResponse(ResponseTypes.SUCCESS, "Permission granted",null);

       }catch (Exception e){
           return new DefaultResponse(ResponseTypes.ERROR, "Error",null);
       }
    }

    @Override
    public DefaultResponse removePermissionFromDoctor(UUID doctorId, UUID patientId) {
        try{
            PatientHasDoctor patientDoctor = mPatientHasDoctorRepo.findPatientHasDoctorByPatientIdAndDoctorId(patientId, doctorId);
            patientDoctor.setIsApproved(0);
            mPatientHasDoctorRepo.save(patientDoctor);
            return new DefaultResponse(ResponseTypes.SUCCESS, "Permission removed ",null);

        }catch (Exception e){
            System.out.println("err"+e.toString());
            return new DefaultResponse(ResponseTypes.ERROR, "Error",null);
        }
    }
}
