package com.msku.drugdosemonitoringsystem.controllers;

import com.msku.drugdosemonitoringsystem.config.BasePath;
import com.msku.drugdosemonitoringsystem.controllers.response.DefaultResponse;
import com.msku.drugdosemonitoringsystem.enums.ResponseTypes;
import com.msku.drugdosemonitoringsystem.services.ISharePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping(BasePath.base + "/patient/permission")
public class Permission {

    @Autowired
    ISharePermissionService mService;

    @PutMapping("/share")
    public ResponseEntity share(@RequestParam("patientId") UUID patientId, @RequestParam("doctorId") UUID doctorId) {
        DefaultResponse response = mService.givePermissionToDoctor( doctorId,patientId);
        if(response.getStatus() == ResponseTypes.SUCCESS) {
            return new ResponseEntity(response, HttpStatus.OK);
        }
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/remove")
    public ResponseEntity revoke(@RequestParam("patientId") UUID patientId, @RequestParam("doctorId") UUID doctorId) {
        DefaultResponse response = mService.removePermissionFromDoctor(doctorId, patientId);
        if(response.getStatus() == ResponseTypes.SUCCESS) {
            return new ResponseEntity(response, HttpStatus.OK);
        }
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);

    }

}
