package com.msku.drugdosemonitoringsystem.controllers.response;

import lombok.Data;

import java.util.UUID;

@Data
public class DoctorInfo {
    private UUID id;
    private String name;
    private String surname;
    private String hospital;
    private String branch;
    private Boolean isApproved;

    public DoctorInfo(UUID id, String name, String surname, String hospital, String branch, Boolean isApproved) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.hospital = hospital;
        this.branch = branch;
        this.isApproved = isApproved;
    }
}
