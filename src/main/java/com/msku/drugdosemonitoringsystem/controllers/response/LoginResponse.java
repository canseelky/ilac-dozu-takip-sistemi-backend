package com.msku.drugdosemonitoringsystem.controllers.response;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class LoginResponse {
    String token;
    UUID userId;
    String email;
    String authority;
    String name;
    String surname;
    String phoneNumber;
    String gender;
    Date dob;


}
