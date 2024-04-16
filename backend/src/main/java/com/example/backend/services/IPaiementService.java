package com.example.backend.services;

import com.example.backend.entities.EtatDePaiement;
import com.example.backend.entities.Paiement;

import java.util.List;

public interface IPaiementService extends AbstractService<Paiement> {
    List<Paiement> getAllPaiementByStudentCode(String code);
    List<Paiement> getAllPaiementByEtat(EtatDePaiement etatDePaiement);
}
