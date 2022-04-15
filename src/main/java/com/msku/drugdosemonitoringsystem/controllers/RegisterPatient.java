package com.msku.drugdosemonitoringsystem.controllers;

import com.msku.drugdosemonitoringsystem.auth.PasswordEncoder;
import com.msku.drugdosemonitoringsystem.config.BasePath;
import com.msku.drugdosemonitoringsystem.controllers.requests.PatientRegister;
import com.msku.drugdosemonitoringsystem.entities.Patient;
import com.msku.drugdosemonitoringsystem.entities.Role;
import com.msku.drugdosemonitoringsystem.repositories.RoleRepo;
import com.msku.drugdosemonitoringsystem.services.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = BasePath.base + "/auth")
public class RegisterPatient {

    @Autowired
    private IPatientService mPatientService;

    @Autowired
    private RoleRepo mRoleRepo;

    private PasswordEncoder passwordEncoder;

    @PostMapping("/patientRegister")
        public ResponseEntity<Patient> RegisterPatient(@RequestBody PatientRegister patient){
        Patient patient1 = mPatientService.getPatientByEmail(patient.getEmail());
            if(patient1 != null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            else{
                Patient patient2 = new Patient();
                patient2.setName(patient.getName());
                patient2.setSurname(patient.getSurname());
                patient2.setPassword(passwordEncoder.passwordEncoder().encode(patient.getPassword()));
                patient2.setCity(patient.getCity());
                patient2.setEmail(patient.getEmail());
                patient2.setPhoneNumber(patient.getPhoneNumber());
                patient2.setGender(patient.getGender());
                patient2.setDateofbirth(patient.getDob());
                patient2.setWeight(patient.getWeight());
                patient2.setHeight(patient.getHeight());
                Role role = mRoleRepo.getById(1L);
                patient2.setRole(role);
                mPatientService.savePatient(patient2);
                return new ResponseEntity<>(patient2,HttpStatus.OK);

            }
    }
}
