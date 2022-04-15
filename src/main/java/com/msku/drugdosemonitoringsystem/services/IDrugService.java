package com.msku.drugdosemonitoringsystem.services;

import com.msku.drugdosemonitoringsystem.controllers.response.DefaultResponse;
import com.msku.drugdosemonitoringsystem.controllers.response.DrugResponse;
import java.util.List;
import java.util.UUID;

public interface IDrugService {

    DefaultResponse<List<DrugResponse>> searchDrugs(String query);
    DefaultResponse<DrugResponse> removeDrug(Long drugId, UUID patientId);

}
