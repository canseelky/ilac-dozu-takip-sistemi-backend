package com.msku.drugdosemonitoringsystem.controllers;

import com.msku.drugdosemonitoringsystem.config.BasePath;
import com.msku.drugdosemonitoringsystem.controllers.requests.ResetPasswordRequest;
import com.msku.drugdosemonitoringsystem.controllers.response.DefaultResponse;
import com.msku.drugdosemonitoringsystem.enums.ResponseTypes;
import com.msku.drugdosemonitoringsystem.services.IPasswordService;
import com.msku.drugdosemonitoringsystem.services.emailservice.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(BasePath.base + "/password")
public class Password {

    @Autowired
    private IPasswordService passwordService;

    @Autowired
   private IEmailService emailService;

    @PutMapping("/change")
    public ResponseEntity<String> change(@RequestBody ResetPasswordRequest request) {
        DefaultResponse response = passwordService.resetPassword(request.getEmail(), request.getPassword());
        if(response.getStatus().equals(ResponseTypes.SUCCESS)){
            return ResponseEntity.ok("Password changed successfully");
        }
        return ResponseEntity.badRequest().body(response.getMessage());
    }
}
