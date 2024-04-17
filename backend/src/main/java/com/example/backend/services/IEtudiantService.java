package com.example.backend.services;

import com.example.backend.dto.EtudiantDTO;

import java.util.List;

public interface IEtudiantService  extends AbstractService<EtudiantDTO> {
    EtudiantDTO findByCode(String code);
    void  save(EtudiantDTO etudiantDTO);

    EtudiantDTO update(Long id, EtudiantDTO etudiantDTO);
}
