package com.msku.drugdosemonitoringsystem.utils;

public class Haycock implements IFormula {

    private final double formulaConstant  = 0.024265;
    private double weight;
    private double height;

    public  Haycock( double weight,double height){
        this.weight=weight;
        this.height=height;
    }


    @Override
    public double calculateBSA() {
        return Math.pow(height,0.3964)*Math.pow(weight,0.5378)*formulaConstant;
    }
}
