package com.msku.drugdosemonitoringsystem.controllers.requests;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String email;
    private String password;
}
