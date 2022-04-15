package com.msku.drugdosemonitoringsystem.controllers.response;

import lombok.Data;

import java.util.Date;

@Data
public class HistoryResponse {
    private Double dose;
    private Date date;
    private String hospital;
    private String drugName;
    private String doctorFullName;
    private Double bsa;
    private Boolean isApproved;

}
