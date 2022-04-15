package com.msku.drugdosemonitoringsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity(name = "history")
@Table(name = "history")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhistory")
    private Long id;
    @Column(name="hospitalname")
    private  String hospitalName;
    private  String city;
    private Double bsa;
    private Double dose;
    private UUID addedby;

    public PatientHistory(String hospitalName, String city, Double bsa, Double dose, UUID addedby, Drug drug, Date date, Patient patient) {
        this.hospitalName = hospitalName;
        this.city = city;
        this.bsa = bsa;
        this.dose = dose;
        this.addedby = addedby;
        this.drug = drug;
        this.date = date;
        this.patient=patient;

    }

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name ="drug_id_fk",referencedColumnName="drug_id")
    private Drug drug;

    private Date date;

    @JsonBackReference
    @ToString.Exclude
   @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
   @JoinColumn(name = "id_patient_fk",referencedColumnName = "id_patient" )
    private Patient patient;

}
