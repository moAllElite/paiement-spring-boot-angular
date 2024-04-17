package com.example.backend.controllers;

import com.example.backend.dto.PaiementDTO;
import com.example.backend.entities.EtatDePaiement;
import com.example.backend.services.IPaiementService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
@RequestMapping("/paiements")
@AllArgsConstructor
@RestController
public class PaiementRestController {
    private final IPaiementService paiementService;

    @GetMapping("etat/paiement")
    public ResponseEntity<List<PaiementDTO>> recupererPaiementsParEtatDePaiement(@RequestParam EtatDePaiement etatDePaiement) {
        return ResponseEntity.ok(paiementService.getAllPaiementByStatus(etatDePaiement));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PaiementDTO> recupererPaiementParIdEtudiant(@PathVariable Long id){
        return ResponseEntity.ok(paiementService.findById(id));
    }
    @GetMapping(value = "fichier/{paiementId}",produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> recupererFichierPaiement(@PathVariable Long paiementId) throws IOException {
        return ResponseEntity.ok(paiementService.getPaiementFileById(paiementId));
    }
    @PutMapping("updateStatus/{id}")
    public ResponseEntity<PaiementDTO> modifierPaiement(@PathVariable Long id, @RequestParam EtatDePaiement etatDePaiement)  {
        return ResponseEntity.ok(paiementService.update(id, etatDePaiement));
    }
    @GetMapping
    public ResponseEntity<List<PaiementDTO>> recupererListeDePaiements() {
        return ResponseEntity.ok(paiementService.findAll());
    }
}
