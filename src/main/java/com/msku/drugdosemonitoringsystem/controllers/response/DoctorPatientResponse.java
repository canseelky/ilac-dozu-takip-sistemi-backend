package com.msku.drugdosemonitoringsystem.controllers.response;

import com.msku.drugdosemonitoringsystem.entities.Drug;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class DoctorPatientResponse {
    private UUID id;
    private String name;
    private String surname;
    private String phoneNumber;
    private List<Drug> drugs;

}
