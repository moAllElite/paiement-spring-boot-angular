package com.example.backend.dto;

import com.example.backend.entities.EtatDePaiement;
import com.example.backend.entities.TypeDePaiement;
import lombok.Builder;
import java.time.LocalDate;

@Builder
public record PaiementDTO(
         Long id,
         LocalDate date,
         double montant,
         TypeDePaiement type,
         EtatDePaiement etat ,
         String recu,
         String codeEtudiant
) { }
