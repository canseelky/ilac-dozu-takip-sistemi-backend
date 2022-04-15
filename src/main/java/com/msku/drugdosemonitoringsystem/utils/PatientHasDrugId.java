package com.msku.drugdosemonitoringsystem.utils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public  class PatientHasDrugId implements Serializable {

    @Column(name = "patient_id")
    protected Long patient_id;

    @Column(name = "drug_id")
    protected Long drug_id;

    public PatientHasDrugId() {
    }

    public PatientHasDrugId(Long patient_id, Long drug_id) {
        this.drug_id = drug_id;
        this.patient_id = patient_id;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public Long getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(Long drug_id) {
        this.drug_id = drug_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientHasDrugId that = (PatientHasDrugId) o;
        return Objects.equals(patient_id, that.patient_id) && Objects.equals(drug_id, that.drug_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patient_id, drug_id);
    }
}