package com.msku.drugdosemonitoringsystem.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient implements User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_patient",updatable = false, nullable = false)
    private  UUID id;
    private String name;
    private String surname;
    private String email;
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String gender;
    private String city;
    private Double weight;
    private Double height;
    private Date dateofbirth;

    @Column(name = "onesignalid")
    private String oneSignalId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="reset_password_id", referencedColumnName = "id")
    private PasswordReset passwordReset;



    @Getter
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="role_id",referencedColumnName = "id")
    private Role role;

    @JsonManagedReference

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "patient")
    private List<PatientHistory> history;


    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<PatientHasDrug> patientHasDrugs;


    @ToString.Exclude
    @JsonManagedReference
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<PatientHasDoctor> patientDoctorList;


    @OneToMany(mappedBy = "patient")
    List<Notification> notifications;


    public void addUserGroup(PatientHasDoctor patientHasDoctor) {
        patientDoctorList.add(patientHasDoctor);
    }
    public void addDrugToPatient(PatientHasDrug patientHasDrug) {
        patientHasDrugs.add(patientHasDrug);
    }

    public void addHistory(PatientHistory historyItem){
        history.add(historyItem);
    }

    public void addNotification(Notification notification){
        notifications.add(notification);
    }

    public List<PatientHistory> getHistoryPatient(){
        return history;
    }

}
