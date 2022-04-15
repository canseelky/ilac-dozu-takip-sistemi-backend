package model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class PermissionsHasPatientEntityPK implements Serializable {
    @Column(name = "permissions_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int permissionsId;
    @Column(name = "patient_id_patient")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientIdPatient;

    public int getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(int permissionsId) {
        this.permissionsId = permissionsId;
    }

    public int getPatientIdPatient() {
        return patientIdPatient;
    }

    public void setPatientIdPatient(int patientIdPatient) {
        this.patientIdPatient = patientIdPatient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionsHasPatientEntityPK that = (PermissionsHasPatientEntityPK) o;

        if (permissionsId != that.permissionsId) return false;
        if (patientIdPatient != that.patientIdPatient) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = permissionsId;
        result = 31 * result + patientIdPatient;
        return result;
    }
}
