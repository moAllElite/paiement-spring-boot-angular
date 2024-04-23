package com.example.backend.services;

import com.example.backend.dto.EtudiantDTO;
import com.example.backend.dto.EtudiantActionsDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

public interface IEtudiantService  extends AbstractService<EtudiantDTO> {
    EtudiantDTO findByCode(String code);
    EtudiantDTO  save(MultipartFile image, String nomComplet, LocalDate dateNaissance) throws IOException;

    EtudiantDTO update(Long id, MultipartFile photo, EtudiantActionsDTO etudiantActionsDTO);
}
