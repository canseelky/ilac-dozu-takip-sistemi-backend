package com.msku.drugdosemonitoringsystem.controllers.response;

import lombok.Data;

@Data
public class DrugResponse {
    private Long id;
    private String activeIngredient;
    private String drugName;
    private String companyName;
    private Long category;
}
