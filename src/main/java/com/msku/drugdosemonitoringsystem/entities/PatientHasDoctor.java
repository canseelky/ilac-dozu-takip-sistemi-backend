package com.msku.drugdosemonitoringsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "patient_has_doctor")
@AllArgsConstructor
@RequiredArgsConstructor

public class PatientHasDoctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "is_approved")
    private int isApproved;


    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="patient_id")
    Patient patient;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="doctor_id")
    Doctor doctor;




}
