package com.msku.drugdosemonitoringsystem.utils;

public class CalculateBsa {

    private final String HAYCOCK = "HAYCOCK";
    private final String DUBOIS = "DUBOIS";
    private final String MOSTELLER = "MOSTELLER";
    private String formulaName;
    private Double height;
    private Double weight;
    private Double bsa;

    public CalculateBsa(String formulaName, Double height, Double weight) {
        this.formulaName = formulaName;
        this.height = height;
        this.weight = weight;
    }

    public Double calculateBSA(){
        switch (this.formulaName) {
            case DUBOIS:
                this.bsa= new DuBois(this.height, this.weight).calculateBSA();
            case MOSTELLER:
                this.bsa=new  Mosteller(this.height, this.weight).calculateBSA();
            case  HAYCOCK :
                this.bsa= new  Haycock(this.height, this.weight).calculateBSA();
        }
        return this.bsa;
    }

}
