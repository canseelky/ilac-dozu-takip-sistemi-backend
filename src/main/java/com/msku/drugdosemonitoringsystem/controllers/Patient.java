package com.msku.drugdosemonitoringsystem.controllers;

import com.msku.drugdosemonitoringsystem.config.BasePath;
import com.msku.drugdosemonitoringsystem.controllers.response.DefaultResponse;
import com.msku.drugdosemonitoringsystem.controllers.response.DoctorInfo;
import com.msku.drugdosemonitoringsystem.controllers.response.HistoryResponse;
import com.msku.drugdosemonitoringsystem.controllers.response.NotificationUser;
import com.msku.drugdosemonitoringsystem.entities.Notification;
import com.msku.drugdosemonitoringsystem.enums.ResponseTypes;
import com.msku.drugdosemonitoringsystem.repositories.PatientRepo;
import com.msku.drugdosemonitoringsystem.services.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping(path = BasePath.base + "/patient")
public class Patient {

    @Autowired
    private IPatientService mPatientService;

    @Autowired
    private PatientRepo mPatientRepo;

    @PutMapping("/data")
    public ResponseEntity<String> updateData(@RequestParam Double weight,  @RequestParam Double height,@RequestParam UUID patientId) {
        mPatientService.updateData(weight,height, patientId);
        return new ResponseEntity<>("Weight successfully added.", HttpStatus.ACCEPTED);
    }

    @Transactional
    @GetMapping("/username")
    public ResponseEntity<String> getUsername(@RequestParam UUID id){
        try{
            com.msku.drugdosemonitoringsystem.entities.Patient patient = mPatientRepo.getById(id);
            return new ResponseEntity<>(patient.getName().toString(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage().toString(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/info")
    public ResponseEntity<com.msku.drugdosemonitoringsystem.entities.Patient> getPersonalInfo(UUID id){
            DefaultResponse<com.msku.drugdosemonitoringsystem.entities.Patient> response = mPatientService.personalInfo(id);
            if(response.getStatus() == ResponseTypes.SUCCESS){
                return new ResponseEntity<>(response.getData(),HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(response.getData(),HttpStatus.BAD_REQUEST);
            }

        }

        @GetMapping("/history")
        public ResponseEntity<List<HistoryResponse>> getHistory(@RequestParam UUID id){
            DefaultResponse<List<HistoryResponse>> response = mPatientService.history(id);
            if(response.getStatus() == ResponseTypes.SUCCESS){
                return new ResponseEntity<List<HistoryResponse>>(response.getData(),HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(response.getData(),HttpStatus.BAD_REQUEST);
            }

        }

    @PostMapping("/setoneSignalId")
    public ResponseEntity<String> setId(@RequestParam UUID id, @RequestParam String oneSignalId){
        DefaultResponse response = mPatientService.setNotificationId(oneSignalId, id);
        if(response.getStatus() == ResponseTypes.SUCCESS){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getDoctors")
    public ResponseEntity<List<DoctorInfo>> getDoctors(@RequestParam UUID id){
        DefaultResponse response = mPatientService.getDoctors(id);
        if(response.getStatus() == ResponseTypes.SUCCESS){
            return new ResponseEntity<List<DoctorInfo>>((List<DoctorInfo>) response.getData(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<DoctorInfo>>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getDoctor")
    public ResponseEntity<DoctorInfo> getDoctor(@RequestParam UUID id){
        DefaultResponse response = mPatientService.getDoctor(id);
        if(response.getStatus() == ResponseTypes.SUCCESS){
            return new ResponseEntity<DoctorInfo>((DoctorInfo) response.getData(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<DoctorInfo>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/notifications")
    public ResponseEntity<List<NotificationUser>> getNotifications(@RequestParam UUID id){
        DefaultResponse response = mPatientService.getNotifications(id);
        if(response.getStatus() == ResponseTypes.SUCCESS){
            return new ResponseEntity<List<NotificationUser>>((List<NotificationUser>) response.getData(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<NotificationUser>>(HttpStatus.BAD_REQUEST);
        }

    }


}
