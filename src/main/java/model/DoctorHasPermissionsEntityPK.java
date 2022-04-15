package model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class DoctorHasPermissionsEntityPK implements Serializable {
    @Column(name = "doctor_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorId;
    @Column(name = "permissions_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int permissionsId;

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPermissionsId() {
        return permissionsId;
    }

    public void setPermissionsId(int permissionsId) {
        this.permissionsId = permissionsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoctorHasPermissionsEntityPK that = (DoctorHasPermissionsEntityPK) o;

        if (doctorId != that.doctorId) return false;
        if (permissionsId != that.permissionsId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = doctorId;
        result = 31 * result + permissionsId;
        return result;
    }
}
