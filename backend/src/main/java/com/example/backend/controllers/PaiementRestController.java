package com.example.backend.controllers;

import com.example.backend.dto.PaiementDTO;
import com.example.backend.entities.EtatDePaiement;
import com.example.backend.services.IPaiementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@RequestMapping("/paiements")
@AllArgsConstructor
@Controller
public class PaiementRestController {
    private final IPaiementService paiementService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PaiementDTO> createPaiement(@RequestParam MultipartFile recu, @RequestBody PaiementDTO paiementDTO) throws IOException {
       return ResponseEntity.ok(paiementService.save(recu,paiementDTO));
    }

    @GetMapping("/etat/paiement")
    public ResponseEntity<List<PaiementDTO>> getAllPaiementsByEtatDePaiement(@RequestParam EtatDePaiement etatDePaiement) {
        return ResponseEntity.ok(paiementService.getAllPaiementByEtat(etatDePaiement));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PaiementDTO> getPaiementByIdEtudiant(@PathVariable Long id){
        return ResponseEntity.ok(paiementService.findById(id));
    }

}
