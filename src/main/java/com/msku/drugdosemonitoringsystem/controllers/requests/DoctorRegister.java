package com.msku.drugdosemonitoringsystem.controllers.requests;

import lombok.Data;

@Data
public class DoctorRegister {
    private String name;
    private String surname;
    private String email;
    private String password;
    private Long hospitalId;
    private String city;


}
