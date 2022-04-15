package model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "patient_has_drug", schema = "drugdosemonitoring", catalog = "")
public class PatientHasDrugEntity {
    @Basic
    @Column(name = "patient_id")
    private Integer patientId;
    @Basic
    @Column(name = "drug_id")
    private Integer drugId;
    @Basic
    @Column(name = "updateFreq")
    private Integer updateFreq;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nextNotifyDate")
    private Timestamp nextNotifyDate;

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

    public Integer getUpdateFreq() {
        return updateFreq;
    }

    public void setUpdateFreq(Integer updateFreq) {
        this.updateFreq = updateFreq;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getNextNotifyDate() {
        return nextNotifyDate;
    }

    public void setNextNotifyDate(Timestamp nextNotifyDate) {
        this.nextNotifyDate = nextNotifyDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatientHasDrugEntity that = (PatientHasDrugEntity) o;

        if (id != that.id) return false;
        if (patientId != null ? !patientId.equals(that.patientId) : that.patientId != null) return false;
        if (drugId != null ? !drugId.equals(that.drugId) : that.drugId != null) return false;
        if (updateFreq != null ? !updateFreq.equals(that.updateFreq) : that.updateFreq != null) return false;
        if (nextNotifyDate != null ? !nextNotifyDate.equals(that.nextNotifyDate) : that.nextNotifyDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = patientId != null ? patientId.hashCode() : 0;
        result = 31 * result + (drugId != null ? drugId.hashCode() : 0);
        result = 31 * result + (updateFreq != null ? updateFreq.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (nextNotifyDate != null ? nextNotifyDate.hashCode() : 0);
        return result;
    }
}
