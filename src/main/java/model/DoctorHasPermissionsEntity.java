package model;

import javax.persistence.*;

@Entity
@Table(name = "doctor_has_permissions", schema = "drugdosemonitoring", catalog = "")
@IdClass(DoctorHasPermissionsEntityPK.class)
public class DoctorHasPermissionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "doctor_id")
    private int doctorId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "permissions_id")
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

        DoctorHasPermissionsEntity that = (DoctorHasPermissionsEntity) o;

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
