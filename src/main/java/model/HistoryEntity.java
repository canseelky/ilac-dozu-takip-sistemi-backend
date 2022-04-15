package model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "history", schema = "drugdosemonitoring", catalog = "")
public class HistoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idhistory")
    private int idhistory;
    @Basic
    @Column(name = "hospitalname")
    private String hospitalname;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "bsa")
    private Double bsa;
    @Basic
    @Column(name = "dose")
    private Double dose;
    @Basic
    @Column(name = "id_patient")
    private Integer idPatient;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "drug_Id")
    private Integer drugId;

    public int getIdhistory() {
        return idhistory;
    }

    public void setIdhistory(int idhistory) {
        this.idhistory = idhistory;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getBsa() {
        return bsa;
    }

    public void setBsa(Double bsa) {
        this.bsa = bsa;
    }

    public Double getDose() {
        return dose;
    }

    public void setDose(Double dose) {
        this.dose = dose;
    }

    public Integer getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Integer idPatient) {
        this.idPatient = idPatient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HistoryEntity that = (HistoryEntity) o;

        if (idhistory != that.idhistory) return false;
        if (hospitalname != null ? !hospitalname.equals(that.hospitalname) : that.hospitalname != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (bsa != null ? !bsa.equals(that.bsa) : that.bsa != null) return false;
        if (dose != null ? !dose.equals(that.dose) : that.dose != null) return false;
        if (idPatient != null ? !idPatient.equals(that.idPatient) : that.idPatient != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (drugId != null ? !drugId.equals(that.drugId) : that.drugId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idhistory;
        result = 31 * result + (hospitalname != null ? hospitalname.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (bsa != null ? bsa.hashCode() : 0);
        result = 31 * result + (dose != null ? dose.hashCode() : 0);
        result = 31 * result + (idPatient != null ? idPatient.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (drugId != null ? drugId.hashCode() : 0);
        return result;
    }
}
