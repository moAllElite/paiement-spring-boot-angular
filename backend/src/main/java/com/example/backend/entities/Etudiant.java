package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter@Setter
@Entity
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String code;
    private String nomComplet;
    private Date dateNaissance;
    private String photo;
    @OneToMany(mappedBy = "etudiant")
    private Set<Paiement> paiements;


}
