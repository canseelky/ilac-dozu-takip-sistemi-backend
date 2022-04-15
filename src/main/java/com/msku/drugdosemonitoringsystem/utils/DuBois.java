package com.msku.drugdosemonitoringsystem.utils;

public class DuBois implements IFormula {
    private final double formulaConstant  = 0.007184;
    private double weight;
    private double height;


    public  DuBois( double weight,double height){
        this.weight=weight;
        this.height=height;
    }

    @Override
    public double calculateBSA() {
        return Math.pow(weight,0.425)*Math.pow(height,0.725)*formulaConstant;
    }
}
