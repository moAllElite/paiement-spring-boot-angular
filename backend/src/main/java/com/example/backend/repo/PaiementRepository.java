package com.example.backend.repo;

import com.example.backend.entities.EtatDePaiement;
import com.example.backend.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findAllByEtudiantCode(String code);
    List<Paiement> findAllByEtat(EtatDePaiement etatDePaiement);
}
