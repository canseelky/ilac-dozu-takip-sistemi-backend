package com.msku.drugdosemonitoringsystem.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "formula")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Formula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formula")
    private Long id;
    private String name;


}
