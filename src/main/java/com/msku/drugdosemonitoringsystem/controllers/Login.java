package com.msku.drugdosemonitoringsystem.controllers;

import com.msku.drugdosemonitoringsystem.auth.JWTTokenGenerator;
import com.msku.drugdosemonitoringsystem.config.BasePath;
import com.msku.drugdosemonitoringsystem.controllers.requests.LoginRequest;
import com.msku.drugdosemonitoringsystem.controllers.requests.VerificationRequest;
import com.msku.drugdosemonitoringsystem.controllers.response.DefaultResponse;
import com.msku.drugdosemonitoringsystem.controllers.response.LoginResponse;
import com.msku.drugdosemonitoringsystem.entities.Doctor;
import com.msku.drugdosemonitoringsystem.entities.Patient;
import com.msku.drugdosemonitoringsystem.enums.ResponseTypes;
import com.msku.drugdosemonitoringsystem.repositories.DoctorRepo;
import com.msku.drugdosemonitoringsystem.repositories.PatientRepo;
import com.msku.drugdosemonitoringsystem.services.IPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = BasePath.base + "/auth")
public class Login {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenGenerator mJWTTokenGenerator;

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private IPasswordService passwordService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        System.out.println(loginRequest.getEmail());
        try{
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String JWTtoken =  mJWTTokenGenerator.createToken(authentication);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setAuthority(authentication.getAuthorities().toArray()[0].toString());
            loginResponse.setToken(JWTtoken);
            loginResponse.setEmail(loginRequest.getEmail());
            loginResponse.setUserId(getUserId(loginRequest.getEmail()));
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    private UUID getUserId(String email){
        UUID id = null;
        Patient patient = patientRepo.findPatientByEmail(email);
        Doctor doctor = doctorRepo.findDoctorByEmail(email);
        if(patient !=  null){
            id = patient.getId();
            return id;
        }
        else{
            id = doctor.getId();
            return id;
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<String> forgot(@RequestParam("email") String email, @RequestParam("password") String password) {
        DefaultResponse response = passwordService.forgotPassword(email, password);
        if(response.getStatus().equals(ResponseTypes.SUCCESS)){
            return ResponseEntity.ok("Email send successfully");
        }
        return ResponseEntity.badRequest().body(response.getMessage());

    }

    @PostMapping("/checkVerificationCode")
    public ResponseEntity<String> reset(@RequestBody VerificationRequest verificationRequest) {
        Boolean response = passwordService.checkVerificationCode(verificationRequest.getEmail(), verificationRequest.getCode());
        if(response){
            return ResponseEntity.ok("Password reset successfully");
        }
        return ResponseEntity.badRequest().body("Invalid verification code");
    }



}


