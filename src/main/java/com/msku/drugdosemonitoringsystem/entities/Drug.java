package com.msku.drugdosemonitoringsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "drug")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drug_id")
    private Long id;
    @Column(name = "active_ingredient")
    private String activeIngredient;
    @Column(name = "base_dose")
    private Double baseDose;

    @Column(name = "drug_name")
    private String drugName;

    @Column(name = "company_name")
    private String companyname;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id_category")
    private Category category;

    @JsonManagedReference
    @ToString.Exclude
    @OneToMany(mappedBy = "drug", cascade = CascadeType.ALL)
    List<PatientHasDrug> drugsPatient;

}
