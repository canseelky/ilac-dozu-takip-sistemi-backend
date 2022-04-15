package com.msku.drugdosemonitoringsystem.services;


import com.msku.drugdosemonitoringsystem.controllers.response.DefaultResponse;
import com.msku.drugdosemonitoringsystem.controllers.response.DrugResponse;
import com.msku.drugdosemonitoringsystem.entities.Drug;
import com.msku.drugdosemonitoringsystem.entities.PatientHasDrug;
import com.msku.drugdosemonitoringsystem.enums.ResponseTypes;
import com.msku.drugdosemonitoringsystem.repositories.DrugRepo;
import com.msku.drugdosemonitoringsystem.repositories.PatientHasDrugRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImplDrugService implements IDrugService {

    @Autowired
    private DrugRepo mDrugRepo;

    @Autowired
    private PatientHasDrugRepo mPatientHasDrugRepo;

    @Override
    public DefaultResponse<List<DrugResponse>> searchDrugs(String query) {
        try{
            List<Drug> drugs = mDrugRepo.findAllByDrugNameLikeL(query);
            List<DrugResponse> drugResponses = new ArrayList<>();

            drugs.stream().forEach(drug ->{
                    DrugResponse drugResponse = new DrugResponse();
                    drugResponse.setDrugName(drug.getDrugName());
                    drugResponse.setId(drug.getId());
                    drugResponse.setActiveIngredient(drug.getActiveIngredient());
                    drugResponse.setCompanyName(drug.getCompanyname());
                    drugResponse.setCategory(drug.getCategory().getId());
                    drugResponses.add(drugResponse);
            });

            return new DefaultResponse<>(ResponseTypes.SUCCESS,"Drugs searched successfully",drugResponses);
        }catch (Exception e){
            e.printStackTrace();
            return new DefaultResponse<>(ResponseTypes.ERROR,"Drugs did not searched successfully",null);
        }
    }

    @Override
    public DefaultResponse<DrugResponse> removeDrug(Long drugId, UUID patientId) {
        try{
            PatientHasDrug patientHasDrug = mPatientHasDrugRepo.findByPatientIdAndDrugId(patientId,drugId);
            mPatientHasDrugRepo.delete(patientHasDrug);
            mPatientHasDrugRepo.save(patientHasDrug);

            return new DefaultResponse<>(ResponseTypes.SUCCESS,"Drug removed successfully",null);
        }catch (Exception e){
            e.printStackTrace();

            return new DefaultResponse<>(ResponseTypes.ERROR,"Drug did not removed successfully",null);
        }
    }
}
