package com.msku.drugdosemonitoringsystem.utils;

import lombok.Data;

@Data
public class AnonymizationPatient {
    private String name;
    private String surname;
    private String city;

    public AnonymizationPatient(String name, String surname, String city) {
        this.name = getAnonymizationInfo(name);
        this.surname = surname = getAnonymizationInfo(surname);
        this.city = city = getAnonymizationInfo(city);
    }



    public String getAnonymizationInfo(String text){
        String hint = text.substring(0,1);
        String remaining = text.substring(1).replaceAll("[a-zA-Z]","*");
        return hint+remaining;
    }
}
