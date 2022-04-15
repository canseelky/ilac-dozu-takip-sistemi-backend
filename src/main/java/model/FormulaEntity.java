package model;

import javax.persistence.*;

@Entity
@Table(name = "formula", schema = "drugdosemonitoring", catalog = "")
public class FormulaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idformula")
    private int idformula;
    @Basic
    @Column(name = "name")
    private String name;

    public int getIdformula() {
        return idformula;
    }

    public void setIdformula(int idformula) {
        this.idformula = idformula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FormulaEntity that = (FormulaEntity) o;

        if (idformula != that.idformula) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idformula;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
