package com.msku.drugdosemonitoringsystem.controllers;

import com.msku.drugdosemonitoringsystem.config.BasePath;
import com.msku.drugdosemonitoringsystem.entities.PatientHistory;
import com.msku.drugdosemonitoringsystem.services.IHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = BasePath.base + "/history")
public class History {

    @Autowired
    IHistoryService historyService;

    @GetMapping("/patientHistory")
    public ResponseEntity<List<PatientHistory>> getPatientHistory(@RequestParam UUID patientId){
        return new ResponseEntity<>(historyService.getHistory(patientId), HttpStatus.OK);

    }
}
