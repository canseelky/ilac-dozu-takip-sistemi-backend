package model;

import javax.persistence.*;

@Entity
@Table(name = "drug", schema = "drugdosemonitoring", catalog = "")
public class DrugEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "drugId")
    private int drugId;
    @Basic
    @Column(name = "activeIngredient")
    private String activeIngredient;
    @Basic
    @Column(name = "baseDose")
    private Double baseDose;
    @Basic
    @Column(name = "drugName")
    private String drugName;
    @Basic
    @Column(name = "companyName")
    private String companyName;
    @Basic
    @Column(name = "category_id")
    private Integer categoryId;
    @Basic
    @Column(name = "ATC_DDD")
    private String atcDdd;

    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drugId) {
        this.drugId = drugId;
    }

    public String getActiveIngredient() {
        return activeIngredient;
    }

    public void setActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }

    public Double getBaseDose() {
        return baseDose;
    }

    public void setBaseDose(Double baseDose) {
        this.baseDose = baseDose;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getAtcDdd() {
        return atcDdd;
    }

    public void setAtcDdd(String atcDdd) {
        this.atcDdd = atcDdd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DrugEntity that = (DrugEntity) o;

        if (drugId != that.drugId) return false;
        if (activeIngredient != null ? !activeIngredient.equals(that.activeIngredient) : that.activeIngredient != null)
            return false;
        if (baseDose != null ? !baseDose.equals(that.baseDose) : that.baseDose != null) return false;
        if (drugName != null ? !drugName.equals(that.drugName) : that.drugName != null) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        if (atcDdd != null ? !atcDdd.equals(that.atcDdd) : that.atcDdd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = drugId;
        result = 31 * result + (activeIngredient != null ? activeIngredient.hashCode() : 0);
        result = 31 * result + (baseDose != null ? baseDose.hashCode() : 0);
        result = 31 * result + (drugName != null ? drugName.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (atcDdd != null ? atcDdd.hashCode() : 0);
        return result;
    }
}
