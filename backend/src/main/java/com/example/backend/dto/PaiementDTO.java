package com.example.backend.dto;

import com.example.backend.entities.EtatDePaiement;
import com.example.backend.entities.Etudiant;
import com.example.backend.entities.TypeDePaiement;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
public record PaiementDTO(
         Long id,
         LocalDate date,
         double montant,
         TypeDePaiement type,
         EtatDePaiement etat ,
         String recu,
         Etudiant etudiant
) { }
