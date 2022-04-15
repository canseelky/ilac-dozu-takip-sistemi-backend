package com.msku.drugdosemonitoringsystem.services;

import com.msku.drugdosemonitoringsystem.entities.Formula;
import java.util.List;
import java.util.UUID;

public interface IFormulaService {

    List<Formula> getFormulas();
    Formula setFormula(UUID doctorid, Long formulaId);
    double calculateBSA(Long formulaId, double weight, double height);

}
