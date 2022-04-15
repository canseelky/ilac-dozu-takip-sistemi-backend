package com.msku.drugdosemonitoringsystem.controllers.response;

import com.msku.drugdosemonitoringsystem.entities.Drug;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
public class PatientSearchResponse {
    private UUID id;
    private String name;
    private String surname;
    private boolean isAlreadyAdded;
    private Date dob;
    private String city;

    public PatientSearchResponse(UUID id, String name, String surname,  boolean isAlreadyAdded,Date dob,String city) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.isAlreadyAdded = isAlreadyAdded;
        this.city = city;
    }
}
