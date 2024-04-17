package com.example.backend.mappers.impl;

import com.example.backend.dto.EtudiantDTO;
import com.example.backend.entities.Etudiant;
import com.example.backend.mappers.IEtudiantMapper;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapperImpl implements IEtudiantMapper {

    @Override
    public Etudiant toEntity(EtudiantDTO etudiantDTO) {
        return  Etudiant.builder()
                .id(etudiantDTO.id())
                .code(etudiantDTO.code())
                .nomComplet(etudiantDTO.nomComplet())
                .dateNaissance(etudiantDTO.dateNaissance())
                .photo(etudiantDTO.photo())
                .build();
    }

    @Override
    public EtudiantDTO toDto(Etudiant etudiant) {
        return EtudiantDTO.builder()
                .id(etudiant.getId())
                .code(etudiant.getCode())
                .nomComplet(etudiant.getNomComplet())
                .dateNaissance(etudiant.getDateNaissance())
                .photo(etudiant.getPhoto())
                .build();
    }
}
