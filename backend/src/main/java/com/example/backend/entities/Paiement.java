package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double montant;
    private TypeDePaiement type;
    private EtatDePaiement etat = EtatDePaiement.CREE;
    private String recu;
    @ManyToOne(fetch = FetchType.EAGER)
    private Etudiant   etudiant;
}
