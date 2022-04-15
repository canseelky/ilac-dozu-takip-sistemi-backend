package com.msku.drugdosemonitoringsystem.controllers;

import com.msku.drugdosemonitoringsystem.config.BasePath;
import com.msku.drugdosemonitoringsystem.controllers.requests.DoctorRegister;
import com.msku.drugdosemonitoringsystem.entities.Doctor;
import com.msku.drugdosemonitoringsystem.entities.Formula;
import com.msku.drugdosemonitoringsystem.entities.Role;
import com.msku.drugdosemonitoringsystem.repositories.FormulaRepo;
import com.msku.drugdosemonitoringsystem.repositories.RoleRepo;
import com.msku.drugdosemonitoringsystem.services.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static com.msku.drugdosemonitoringsystem.auth.PasswordEncoder.passwordEncoder;

@Controller
@RequestMapping(path = BasePath.base + "/auth")

public class RegisterDoctor {

    @Autowired
    IDoctorService mDoctorService;

    @Autowired
    private RoleRepo mRoleRepo;

    @Autowired
    private FormulaRepo formulaRepo;

    @PostMapping("/doctor-register")
    public ResponseEntity<String> registerDoctor(@RequestBody DoctorRegister doctorRegisterInfo){
        Boolean isEmailInUse = mDoctorService.checkEmailInUse(doctorRegisterInfo.getEmail());
        if(isEmailInUse){
           return new  ResponseEntity<>("E mail address already in use", HttpStatus.BAD_REQUEST);
        }
        else {
            try{
                Doctor doctor = new Doctor();
                doctor.setName(doctorRegisterInfo.getName());
                doctor.setSurname(doctorRegisterInfo.getSurname());
                doctor.setEmail(doctorRegisterInfo.getEmail());
                doctor.setPassword(passwordEncoder().encode(doctorRegisterInfo.getPassword()));
                Role role = mRoleRepo.getById(2L);
                Formula formula = formulaRepo.getById(1L);

                doctor.setFormula(formula);
                doctor.setRole(role);
                mDoctorService.save(doctor);
                return new  ResponseEntity<>("User successfully registered", HttpStatus.CREATED);
            }catch (Exception e){
                System.out.println(e.getMessage());
                return new  ResponseEntity<>("Error occurred", HttpStatus.CREATED);
            }

        }
    }

}
