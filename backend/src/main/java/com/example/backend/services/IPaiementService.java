package com.example.backend.services;

import com.example.backend.dto.PaiementDTO;
import com.example.backend.entities.EtatDePaiement;
import com.example.backend.entities.TypeDePaiement;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface IPaiementService extends AbstractService<PaiementDTO> {
    List<PaiementDTO> getAllPaiementByStudentCode(String code);
    List<PaiementDTO> getAllPaiementByStatus(EtatDePaiement etatDePaiement);
    PaiementDTO  save(MultipartFile recu, LocalDate date,double montant, TypeDePaiement typeDePaiement, String codeEtudiant) throws IOException;
    byte[] getPaiementFileById(Long paiementId) throws IOException;
    PaiementDTO update(Long id, EtatDePaiement etatDePaiement);
}
