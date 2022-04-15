package com.msku.drugdosemonitoringsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "patient_has_drug")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientHasDrug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    Patient patient;


    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "drug_id")
    Drug drug;

    @Column(name = "updatefreq")
    private Integer updateFeq;

    @Column(name = "nextnotifydate")
    private LocalDate nextNotifyDate;

    private UUID addedby;

    private double base_dose;


}

