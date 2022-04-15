package com.msku.drugdosemonitoringsystem.controllers.requests;

import lombok.Data;

@Data
public class VerificationRequest {
    private String code;
    private String email;
}
