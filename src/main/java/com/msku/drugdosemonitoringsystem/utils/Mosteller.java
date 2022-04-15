package com.msku.drugdosemonitoringsystem.utils;

public class Mosteller implements IFormula {
    private final double formulaConstant  = 0.007184;
    private double weight;
    private double height;

    public  Mosteller( double weight,double height){
        this.weight=weight;
        this.height=height;
    }

    @Override
    public double calculateBSA() {
        return Math.pow(height*weight/3600,0.5);
    }
}
