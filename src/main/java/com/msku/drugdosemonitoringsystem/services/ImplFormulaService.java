package com.msku.drugdosemonitoringsystem.services;

import com.msku.drugdosemonitoringsystem.entities.Doctor;
import com.msku.drugdosemonitoringsystem.entities.Formula;
import com.msku.drugdosemonitoringsystem.repositories.DoctorRepo;
import com.msku.drugdosemonitoringsystem.repositories.FormulaRepo;
import com.msku.drugdosemonitoringsystem.utils.DuBois;
import com.msku.drugdosemonitoringsystem.utils.Haycock;
import com.msku.drugdosemonitoringsystem.utils.IFormula;
import com.msku.drugdosemonitoringsystem.utils.Mosteller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ImplFormulaService implements IFormulaService{

    @Autowired
    private FormulaRepo formulaRepo;

    @Autowired
    private DoctorRepo mDoctorRepo;

    @Override
    public List<Formula> getFormulas() {
        List<Formula> formulas = formulaRepo.findAll();
        return formulas;
    }

    @Override
    public Formula setFormula(UUID doctorId, Long formulaId) {
        Doctor doctor = mDoctorRepo.getById(doctorId);
        Formula formula = formulaRepo.getById(formulaId);
        doctor.setFormula(formula);
        mDoctorRepo.save(doctor);
        return formula;
    }

    public double calculateBSA(Long formulaId, double weight, double height) {
        Formula formula = formulaRepo.getById(formulaId);
        IFormula selectedFormula;
        double bsa;

        if (formulaId == 1) {
            selectedFormula = new DuBois(weight, height);
            bsa = selectedFormula.calculateBSA();
        } else if (formulaId == 2) {
            selectedFormula = new Haycock(weight, height);
            bsa = selectedFormula.calculateBSA();
        } else {
            selectedFormula = new Mosteller(weight, height);
            bsa = selectedFormula.calculateBSA();
        }
        return bsa;
    }

    /*    public  void calculateBSA(Long formulaId){
        Formula formula = formulaRepo.getById(formulaId);

        switch (formulaId) {
            case 1:

                break;

            case FormulaType.HEYCOCK:
                System.out.println("Fridays are better.");
                break;

            case FormulaType.MOSTELLER:
                System.out.println("Weekends are best.");
                break;

            default:
                System.out.println("Midweek days are so-so.");
                break;
        }*/
}
