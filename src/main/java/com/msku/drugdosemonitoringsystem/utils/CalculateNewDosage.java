package com.msku.drugdosemonitoringsystem.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CalculateNewDosage {

    private Double height;
    private Double weight;
    private Double bsa;
    private String formulaName;
    private Double dose;
    private Double baseDose;
    public CalculateNewDosage(String formulaName, Double height, Double weight,Double baseDose) {
        this.formulaName = formulaName;
        this.height = height;
        this.weight = weight;
        this.baseDose = baseDose;
        this.bsa =  new CalculateBsa(formulaName,height, weight).calculateBSA();
    }

    public Double calculateDose (){
        CalculateBsa calculateBsa = new CalculateBsa(formulaName,height, weight);
        bsa = calculateBsa.calculateBSA();
        dose =  bsa * baseDose;
        return dose;

    }



}
