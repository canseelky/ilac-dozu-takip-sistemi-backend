package com.msku.drugdosemonitoringsystem.controllers;

import com.msku.drugdosemonitoringsystem.config.BasePath;
import com.msku.drugdosemonitoringsystem.controllers.response.DefaultResponse;
import com.msku.drugdosemonitoringsystem.controllers.response.DrugResponse;
import com.msku.drugdosemonitoringsystem.entities.Drug;
import com.msku.drugdosemonitoringsystem.enums.ResponseTypes;
import com.msku.drugdosemonitoringsystem.services.IDoctorService;
import com.msku.drugdosemonitoringsystem.services.IDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
 @RequestMapping(path = BasePath.base+ "/doctor/drug")
public class DrugController {

    @Autowired
    private IDrugService mDrugService;

    @Autowired
    private IDoctorService mDoctorService;


    @GetMapping("/search")
    public ResponseEntity<List<DrugResponse>> getDrugs(@RequestParam String term){
        DefaultResponse<List<DrugResponse>> response = mDrugService.searchDrugs(term);
        if(response.getStatus()== ResponseTypes.SUCCESS){
            return ResponseEntity.ok(response.getData());
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/addDrugToPatient")
    public ResponseEntity<Drug> addDrugToPatient(@RequestParam UUID doctorid, @RequestParam UUID patientid, @RequestParam Long drugid, @RequestParam int updateFreq, @RequestParam int baseDose) {
        System.out.println("doctorid: "+doctorid+" patientid: "+patientid+" drugid: "+drugid+" updateFreq: "+updateFreq+" baseDose: "+baseDose);
            DefaultResponse response = mDoctorService.addDrugToPatient(doctorid, patientid, drugid,updateFreq,baseDose);
            if (response.getStatus() == ResponseTypes.SUCCESS) {
                return new ResponseEntity<Drug>((Drug) response.getData(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

    }

    @DeleteMapping("/removeDrug")
    public ResponseEntity<String> removeDrugFromPatient( @RequestParam UUID patientid, @RequestParam Long drugid) {
          DefaultResponse response = mDrugService.removeDrug(drugid,patientid);
                 if(response.getStatus()== ResponseTypes.SUCCESS){
                     return new ResponseEntity<>("Success", HttpStatus.OK);
                 }
                 else{
                     return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
                 }
    }

}
