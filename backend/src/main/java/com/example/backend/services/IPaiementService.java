package com.example.backend.services;

import com.example.backend.dto.PaiementDTO;
import com.example.backend.entities.EtatDePaiement;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IPaiementService extends AbstractService<PaiementDTO> {
    List<PaiementDTO> getAllPaiementByStudentCode(String code);
    List<PaiementDTO> getAllPaiementByEtat(EtatDePaiement etatDePaiement);

    PaiementDTO  save(MultipartFile recu, PaiementDTO paiementDTO) throws IOException;
}
