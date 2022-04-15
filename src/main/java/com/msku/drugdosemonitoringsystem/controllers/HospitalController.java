package com.msku.drugdosemonitoringsystem.controllers;

import com.msku.drugdosemonitoringsystem.config.BasePath;
import com.msku.drugdosemonitoringsystem.entities.Hospital;
import com.msku.drugdosemonitoringsystem.repositories.HospitalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping(BasePath.base + "/hospital")
public class HospitalController {

    @Autowired
    private HospitalRepo mHospitalRepo;

   @GetMapping("/all")
    public ResponseEntity<List<Hospital>> getHospitals() {
        List<com.msku.drugdosemonitoringsystem.entities.Hospital> hospitals = mHospitalRepo.findAll();
        return ResponseEntity.ok(hospitals);
    }


}
