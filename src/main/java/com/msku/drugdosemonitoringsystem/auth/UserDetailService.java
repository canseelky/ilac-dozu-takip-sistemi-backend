package com.msku.drugdosemonitoringsystem.auth;

import com.msku.drugdosemonitoringsystem.entities.Doctor;
import com.msku.drugdosemonitoringsystem.entities.Patient;
import com.msku.drugdosemonitoringsystem.repositories.DoctorRepo;
import com.msku.drugdosemonitoringsystem.repositories.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient = patientRepo.findPatientByEmail(username);
        if(patient !=  null){
            UserDetails user_p = UserDetail.createUserDetail(patient);
            return  user_p;
        }
        Doctor doctor = doctorRepo.findDoctorByEmail(username);
        if(doctor != null){
            UserDetails user_d = UserDetail.createUserDetail(doctor);
            return user_d;
        }
        return null;
    }

    public UserDetails loadUserByEmail(String email) throws Exception{
        Patient patient = patientRepo.findPatientByEmail(email);
        if(patient !=  null){
            UserDetails user_p = UserDetail.createUserDetail(patient);
            return  user_p;
        }
        Doctor doctor = doctorRepo.findDoctorByEmail(email);
        if(doctor != null){
            UserDetails user_d = UserDetail.createUserDetail(doctor);
            return user_d;
        }
    return null;
    }

    public UserDetails loadUserById(UUID id) throws Exception{
        try{
            Doctor doctor = doctorRepo.getById(id);
            if(doctor != null){
                UserDetails user_d = UserDetail.createUserDetail(doctor);
                System.out.println("err"+doctor);
                return user_d;
            }
        }catch (Exception e){
            System.out.println("err"+e);
        }
        try{
            Patient patient = patientRepo.getById(id);
            if(patient != null){
                UserDetails user_p = UserDetail.createUserDetail(patient);
                return user_p;
            }
        }catch (Exception e){
            System.out.println("**"+e);
        }
        return null;
    }


}
