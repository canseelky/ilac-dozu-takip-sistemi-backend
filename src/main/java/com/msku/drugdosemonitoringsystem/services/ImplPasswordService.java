package com.msku.drugdosemonitoringsystem.services;

import com.msku.drugdosemonitoringsystem.auth.PasswordEncoder;
import com.msku.drugdosemonitoringsystem.controllers.response.DefaultResponse;
import com.msku.drugdosemonitoringsystem.entities.Doctor;
import com.msku.drugdosemonitoringsystem.entities.PasswordReset;
import com.msku.drugdosemonitoringsystem.entities.Patient;
import com.msku.drugdosemonitoringsystem.entities.User;
import com.msku.drugdosemonitoringsystem.enums.ResponseTypes;
import com.msku.drugdosemonitoringsystem.repositories.DoctorRepo;
import com.msku.drugdosemonitoringsystem.repositories.PatientRepo;
import com.msku.drugdosemonitoringsystem.services.emailservice.IEmailService;
import com.msku.drugdosemonitoringsystem.utils.VerificationCodeGenarator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
public class ImplPasswordService implements IPasswordService{

    @Autowired
    private PatientRepo mPatientRepo;

    @Autowired
    private DoctorRepo mDoctorRepo;

    @Autowired
    private IEmailService emailService;

    private VerificationCodeGenarator codeGenarator = new VerificationCodeGenarator();


    @Override
    public DefaultResponse<User> forgotPassword(String email, String password) {
        Patient patient = mPatientRepo.findPatientByEmail(email);
        if(patient != null){
            String token = codeGenarator.generateVerificationCode();
            PasswordReset passwordReset = new PasswordReset();
            passwordReset.setRemainingPassword(PasswordEncoder.passwordEncoder().encode(password));
            passwordReset.setToken(token);
            passwordReset.setExpiredAt(LocalDateTime.now().plusHours(1).toLocalDate());
            patient.setPasswordReset(passwordReset);
            mPatientRepo.save(patient);
            try{
                emailService.sendEmailToUser(email, token);
                return new DefaultResponse<User>(ResponseTypes.SUCCESS,"password reaminin added",patient);
            }
            catch(Exception e){
                return new DefaultResponse<User>(ResponseTypes.ERROR," email did nnot send",patient);
            }
        }
        Doctor doctor = mDoctorRepo.findDoctorByEmail(email);
        if(doctor != null){
            String token = codeGenarator.generateVerificationCode();
            PasswordReset passwordReset = new PasswordReset();
            passwordReset.setRemainingPassword(PasswordEncoder.passwordEncoder().encode(password));
            passwordReset.setToken(token);
            passwordReset.setExpiredAt(LocalDateTime.now().plusDays(1).toLocalDate());
            doctor.setPasswordReset(passwordReset);
            mDoctorRepo.save(doctor);
            try{
                emailService.sendEmailToUser(email, token);
                return new DefaultResponse<User>(ResponseTypes.SUCCESS,"password reaminin added",doctor);
            }
            catch(Exception e){
                return new DefaultResponse<User>(ResponseTypes.ERROR," email did not send",doctor);
            }
        }

        return new DefaultResponse<User>(ResponseTypes.ERROR,"email not found",null);

    }

    @Override
    public DefaultResponse<Boolean> resetPassword(String email, String password) {

        Patient patient = mPatientRepo.findPatientByEmail(email);
        if(patient != null){
            patient.setPassword(PasswordEncoder.passwordEncoder().encode(password));
            mPatientRepo.save(patient);
            return new DefaultResponse<Boolean>(ResponseTypes.SUCCESS,"password reset",true);
        }
        Doctor doctor = mDoctorRepo.findDoctorByEmail(email);
        if(doctor != null){

            doctor.setPassword(PasswordEncoder.passwordEncoder().encode(password));
            mDoctorRepo.save(doctor);
            return new DefaultResponse<Boolean>(ResponseTypes.SUCCESS,"password reset",true);

        }
        return new DefaultResponse<Boolean>(ResponseTypes.ERROR,"email not found",false);
    }

    @Override
    public Boolean checkVerificationCode(String email, String verificationCode) {
        Patient patient = mPatientRepo.findPatientByEmail(email);
        if(patient != null ){
            if (patient.getPasswordReset().getToken().equals(verificationCode)  ){
                String remainingPassword = patient.getPasswordReset().getRemainingPassword();
                patient.setPassword(remainingPassword);
                mPatientRepo.save(patient);
                return true;
            } else {
                return false;
            }
        }
        Doctor doctor = mDoctorRepo.findDoctorByEmail(email);
        if(doctor != null ){
            if(doctor.getPasswordReset().getToken().equals(verificationCode )  ){
                String remainingPassword = doctor.getPasswordReset().getRemainingPassword();
                doctor.setPassword(remainingPassword);
                mDoctorRepo.save(doctor);
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
}
