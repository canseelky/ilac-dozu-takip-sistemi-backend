package model;

import javax.persistence.*;

@Entity
@Table(name = "permissions_has_patient", schema = "drugdosemonitoring", catalog = "")
@IdClass(PermissionsHasPatientEntityPK.class)
public class PermissionsHasPatientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "permissions_id")
    private int permissionsId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "patient_id_patient")
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

        PermissionsHasPatientEntity that = (PermissionsHasPatientEntity) o;

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
