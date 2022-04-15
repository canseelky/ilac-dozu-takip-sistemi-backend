package com.msku.drugdosemonitoringsystem.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
@Entity
@Table(name="notification")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnotification")
    private Long id;



    @Column(name = "notificationtype")
    private String notificationType;

    @Column(name = "notifydate")
    private LocalDate notifyDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "isRead")
    private Integer isRead = 0;


    @Column(name = "content")
    private String content;



}
