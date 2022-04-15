package com.msku.drugdosemonitoringsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;


@Entity(name = "resetPassword")
@Table(name = "resetPassword")
@Data
public class PasswordReset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    @Column(name = "expired_at")
    private LocalDate expiredAt;

    @OneToOne(mappedBy = "passwordReset")
    private Doctor doctor;

    @JsonBackReference
    @OneToOne(mappedBy = "passwordReset")
    private Patient patient;

    @Column(name = "remaining_password")
    private String remainingPassword;

    @Override
    public String toString() {
        return "PasswordReset{";
    }
}


