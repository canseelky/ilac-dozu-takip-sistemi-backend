package com.msku.drugdosemonitoringsystem.controllers.requests;

import lombok.Data;

import java.util.Date;

@Data
public class PatientRegister {
    private String name;
    private String surname;
    private  String email;
    private Date dob;
    private String gender;
    private String phoneNumber;
    private String city;
    private String password;
    private Double weight;
    private Double height;

}
