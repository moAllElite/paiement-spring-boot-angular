package com.example.backend.mappers.impl;

import com.example.backend.dto.PaiementDTO;
import com.example.backend.entities.Etudiant;
import com.example.backend.entities.Paiement;
import com.example.backend.mappers.IEtudiantMapper;
import com.example.backend.mappers.IPaiementMapper;
import com.example.backend.repo.EtudiantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class PaiementMapperImpl implements IPaiementMapper {
    private final IEtudiantMapper etudiantMapper;
    private final EtudiantRepository etudiantRepository;
    @Override
    public Paiement toEntity(PaiementDTO paiementDTO) {
      Etudiant etudiant = etudiantRepository.findByCode(paiementDTO.etudiant().code())
                .orElseThrow(() -> new EntityNotFoundException("Etudiant est introuvable"));


        return Paiement.builder()
                .id(paiementDTO.id())
                .date(paiementDTO.date())
                .type(paiementDTO.type())
                .etat(paiementDTO.etat())
                .recu(paiementDTO.recu())
                .montant(paiementDTO.montant())
                .etudiant(etudiant)
                .build();
    }

    @Override
    public PaiementDTO toDto(Paiement paiement) {

        return PaiementDTO.builder()
                .id(paiement.getId())
                .date(paiement.getDate())
                .etat(paiement.getEtat())
                .montant(paiement.getMontant())
                .type(paiement.getType())
                .etudiant(etudiantMapper.toDto(paiement.getEtudiant()))
                .recu(paiement.getRecu())
                .build();
    }


}
