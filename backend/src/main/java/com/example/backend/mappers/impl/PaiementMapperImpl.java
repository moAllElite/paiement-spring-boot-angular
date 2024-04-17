package com.example.backend.mappers.impl;

import com.example.backend.dto.PaiementDTO;
import com.example.backend.entities.Etudiant;
import com.example.backend.entities.Paiement;
import com.example.backend.mappers.IPaiementMapper;
import com.example.backend.repo.EtudiantRepository;
import com.example.backend.repo.PaiementRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class PaiementMapperImpl implements IPaiementMapper {
    private final EtudiantRepository etudiantRepository;
    @Override
    public Paiement toEntity(PaiementDTO paiementDTO) {
        Etudiant etudiant = etudiantRepository.findByCode(paiementDTO.codeEtudiant())
                .orElseThrow(() -> new EntityNotFoundException("Etudiant est introuvable"));

        return Paiement.builder()
                .id(paiementDTO.id())
                .date(paiementDTO.date())
                .etat(paiementDTO.etat())
                .recu(paiementDTO.recu())
                .etudiant(etudiant)
                .build();
    }

    @Override
    public PaiementDTO toDto(Paiement paiement) {
        return PaiementDTO.builder()
                .id(paiement.getId())
                .date(paiement.getDate())
                .etat(paiement.getEtat())
                .codeEtudiant(paiement.getEtudiant().getCode())
                .recu(paiement.getRecu())
                .build();
    }
}
