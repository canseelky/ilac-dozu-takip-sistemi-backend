package com.msku.drugdosemonitoringsystem.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "doctor", schema = "drugdosemonitoring")
@NoArgsConstructor
@AllArgsConstructor

@Data
public class Doctor implements User{

    @Id
    @Column(name = "iddoctor",updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "name")
    private String name;



    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "dateofbirth")
    private Date dateofbirth;


    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    private String onesignalid;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "hospital_id", referencedColumnName = "idhospital")
    private Hospital hospital;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "formula_id", referencedColumnName = "id_formula")
    private Formula formula;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "reset_password_id", referencedColumnName = "id")
    private PasswordReset passwordReset;


    @Getter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;


    @ToString.Exclude
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<PatientHasDoctor> doctorPatientList;

    public void addUserGroup(PatientHasDoctor patientHasDoctor) {
        doctorPatientList.add(patientHasDoctor);
    }


    @ToString.Exclude
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    List<Notification> notifications;

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", dateofbirth=" + dateofbirth +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", onesignalid='" + onesignalid + '\'' +
                ", hospital=" + hospital +
                ", formula=" + formula +
                ", role=" + role +

                ", notifications=" + notifications +
                '}';
    }
}
