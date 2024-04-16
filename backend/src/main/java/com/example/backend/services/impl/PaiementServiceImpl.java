package com.example.backend.services.impl;

import com.example.backend.entities.EtatDePaiement;
import com.example.backend.entities.Paiement;
import com.example.backend.repo.PaiementRepository;
import com.example.backend.services.IPaiementService;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class PaiementServiceImpl implements IPaiementService {
    private final PaiementRepository paiementRepository;
    @Override
    public List<Paiement> getAllPaiementByStudentCode(String code) {
        return List.of();
    }

    @Override
    public List<Paiement> getAllPaiementByEtat(EtatDePaiement etatDePaiement) {
        return List.of();
    }

    @Override
    public void save(Paiement paiement) {

    }

    @Override
    public Paiement findById(Long id) {
        return null;
    }

    @Override
    public List<Paiement> findAll() {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Paiement update(Long id, Paiement paiement) {
        return null;
    }
}
