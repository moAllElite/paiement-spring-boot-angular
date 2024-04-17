package com.example.backend.services;

import com.example.backend.dto.EtudiantDTO;

public interface IEtudiantService  extends AbstractService<EtudiantDTO> {
    EtudiantDTO findByCode(String code);
    void  save(EtudiantDTO etudiantDTO);
}
