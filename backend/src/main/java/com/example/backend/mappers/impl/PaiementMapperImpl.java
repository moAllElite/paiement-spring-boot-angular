package com.example.backend.mappers.impl;

import com.example.backend.dto.PaiementDTO;
import com.example.backend.entities.Paiement;
import com.example.backend.mappers.IPaiementMapper;
import org.springframework.stereotype.Component;

@Component
public class PaiementMapperImpl implements IPaiementMapper {
    @Override
    public Paiement toEntity(PaiementDTO paiementDTO) {
        return Paiement.builder()
                .id(paiementDTO.id())
                .date(paiementDTO.date())
                .etat(paiementDTO.etat())
                .recu(paiementDTO.recu())
                .build();
    }

    @Override
    public PaiementDTO toDto(Paiement paiement) {
        return PaiementDTO.builder()
                .id(paiement.getId())
                .date(paiement.getDate())
                .etat(paiement.getEtat())
                .recu(paiement.getRecu())
                .build();
    }
}
