package model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "notification", schema = "drugdosemonitoring", catalog = "")
public class NotificationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idnotification")
    private int idnotification;
    @Basic
    @Column(name = "notificationType")
    private String notificationType;
    @Basic
    @Column(name = "notifyDate")
    private Date notifyDate;
    @Basic
    @Column(name = "doctor_id")
    private Integer doctorId;
    @Basic
    @Column(name = "patient_id")
    private Integer patientId;

    public int getIdnotification() {
        return idnotification;
    }

    public void setIdnotification(int idnotification) {
        this.idnotification = idnotification;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public Date getNotifyDate() {
        return notifyDate;
    }

    public void setNotifyDate(Date notifyDate) {
        this.notifyDate = notifyDate;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotificationEntity that = (NotificationEntity) o;

        if (idnotification != that.idnotification) return false;
        if (notificationType != null ? !notificationType.equals(that.notificationType) : that.notificationType != null)
            return false;
        if (notifyDate != null ? !notifyDate.equals(that.notifyDate) : that.notifyDate != null) return false;
        if (doctorId != null ? !doctorId.equals(that.doctorId) : that.doctorId != null) return false;
        if (patientId != null ? !patientId.equals(that.patientId) : that.patientId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idnotification;
        result = 31 * result + (notificationType != null ? notificationType.hashCode() : 0);
        result = 31 * result + (notifyDate != null ? notifyDate.hashCode() : 0);
        result = 31 * result + (doctorId != null ? doctorId.hashCode() : 0);
        result = 31 * result + (patientId != null ? patientId.hashCode() : 0);
        return result;
    }
}
