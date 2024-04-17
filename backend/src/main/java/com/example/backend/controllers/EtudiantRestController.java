package com.example.backend.controllers;

import com.example.backend.dto.EtudiantDTO;
import com.example.backend.dto.PaiementDTO;
import com.example.backend.entities.TypeDePaiement;
import com.example.backend.services.IEtudiantService;
import com.example.backend.services.IPaiementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("etudiants")
public class EtudiantRestController {
    private final IEtudiantService  etudiantService;
    private final IPaiementService paiementService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody EtudiantDTO etudiantDto){
        etudiantService.save(etudiantDto);
    }

    @GetMapping("")
    public ResponseEntity<List<EtudiantDTO>> getAll(){
        return ResponseEntity.ok(etudiantService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<EtudiantDTO> getEtudiantById(@RequestParam long id){
        return ResponseEntity.ok(etudiantService.findById(id));
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long  id){
          etudiantService.delete(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody EtudiantDTO etudiantDto){
         etudiantService.update(id, etudiantDto);
         return ResponseEntity.noContent().build();
    }

    @GetMapping("{code}/paiements/")
    public ResponseEntity<List<PaiementDTO>> getPaiementByCodeEtudiant(@PathVariable String code){
        return ResponseEntity.ok(paiementService.getAllPaiementByStudentCode(code));
    }

    @PostMapping(value = "/paiements", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PaiementDTO> nouveauPaiement(@RequestParam MultipartFile recu, LocalDate date, double montant, TypeDePaiement typeDePaiement, String codeEtudiant) throws IOException {
        return ResponseEntity.ok(paiementService.save(recu,  date, montant, typeDePaiement, codeEtudiant));
    }
}
