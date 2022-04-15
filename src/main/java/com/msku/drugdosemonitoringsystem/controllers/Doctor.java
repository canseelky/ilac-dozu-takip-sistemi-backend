package com.msku.drugdosemonitoringsystem.controllers;

import com.msku.drugdosemonitoringsystem.config.BasePath;
import com.msku.drugdosemonitoringsystem.controllers.response.*;
import com.msku.drugdosemonitoringsystem.entities.*;
import com.msku.drugdosemonitoringsystem.entities.Patient;
import com.msku.drugdosemonitoringsystem.enums.ResponseTypes;
import com.msku.drugdosemonitoringsystem.repositories.DoctorRepo;
import com.msku.drugdosemonitoringsystem.repositories.PatientHasDoctorRepo;
import com.msku.drugdosemonitoringsystem.repositories.PatientRepo;
import com.msku.drugdosemonitoringsystem.services.IDoctorService;
import com.msku.drugdosemonitoringsystem.services.IFormulaService;
import com.msku.drugdosemonitoringsystem.services.IPatientService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = BasePath.base + "/doctor")
@Api(value="User Rest Service")
public class Doctor {

    @Autowired
    private IDoctorService mDoctorService;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private PatientHasDoctorRepo patientHasDoctorRepo;

    @Autowired
    private IFormulaService mFormulaService;

    @Autowired
    private IPatientService mPatientService;

    @GetMapping("/search")
    public ResponseEntity<PatientSearchResponse> searchPatient(@RequestParam String phoneNumber, @RequestParam UUID doctorid) {
        PatientSearchResponse patient = mDoctorService.searchPatient(phoneNumber,doctorid);
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> all() {
        List<Patient> patients = mDoctorService.getAll();
        if (patients == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }



    @GetMapping("/formulas")
    public ResponseEntity<List<Formula>> getAllFormula() {
        List<Formula> formulas = mFormulaService.getFormulas();
        if (formulas == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(formulas, HttpStatus.OK);

    }

    @PostMapping("/setFormula")
    public ResponseEntity<Formula> setFormulaToDoctor(@RequestParam UUID doctorid, @RequestParam Long formulaId) {

        Formula formula = mFormulaService.setFormula(doctorid, formulaId);

        if (formula == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(formula, HttpStatus.OK);

    }

    @GetMapping("/patients")
    public  ResponseEntity<List<DoctorPatientResponse>>  getPatients(@RequestParam UUID doctorid){
        List<DoctorPatientResponse> pa = mDoctorService.seePatients(doctorid);
        return new ResponseEntity<>(pa,HttpStatus.OK);

    }


    @PostMapping("/setoneSignalId")
    public ResponseEntity<String> setNotificationId(@RequestParam String oneSignalId, @RequestParam UUID id) {

        DefaultResponse response = mDoctorService.setNotificationId(oneSignalId, id);
        if(response.getStatus() == ResponseTypes.SUCCESS) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

@GetMapping("/doctor-formula")
public ResponseEntity<Long> getSelectedFormula(@RequestParam UUID doctorId){
    DefaultResponse formulaRes = mDoctorService.getSelectedFormula(doctorId);
    if(formulaRes.getStatus() == ResponseTypes.SUCCESS) {

        return new ResponseEntity<Long>((Long) formulaRes.getData(), HttpStatus.OK);

    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
}


    @GetMapping("/request")
    public ResponseEntity<Long> sendRequestToPatient(@RequestParam UUID doctorid, @RequestParam UUID patientid) {
        DefaultResponse response = mDoctorService.sendRequestToPatient(patientid, doctorid);
        if(response.getStatus() == ResponseTypes.SUCCESS) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/setFormula")
    public ResponseEntity<String> setFormula(@RequestParam Long formulaId, @RequestParam UUID doctorId) {

        DefaultResponse response = mDoctorService.setFormula( formulaId,doctorId);
        if(response.getStatus() == ResponseTypes.SUCCESS) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        System.out.println("error" + response.getStatus());
        return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/patientsPage")
    public ResponseEntity<List<DoctorPatientResponse>> getPatients(@RequestParam UUID doctorid, @RequestParam int page) {
        try{
            List<DoctorPatientResponse> patients = mDoctorService.getPatientList(doctorid,page);
            if (patients == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(patients, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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

    @GetMapping("/notifications")
    public ResponseEntity<List<NotificationUser>> getNotifications(@RequestParam  UUID doctorId){
        DefaultResponse<List<NotificationUser>> response = mDoctorService.getNotifications(doctorId);
        if(response.getStatus() == ResponseTypes.SUCCESS){
            return new ResponseEntity<List<NotificationUser>>(response.getData(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(response.getData(),HttpStatus.BAD_REQUEST);
        }

    }

}
