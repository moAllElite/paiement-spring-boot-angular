package com.example.backend.controllers;

import com.example.backend.dto.EtudiantDTO;
import com.example.backend.dto.EtudiantActionsDTO;
import com.example.backend.dto.PaiementDTO;
import com.example.backend.entities.TypeDePaiement;
import com.example.backend.services.IEtudiantService;
import com.example.backend.services.IPaiementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/etudiants")
public class EtudiantRestController {
    private final IEtudiantService  etudiantService;
    private final IPaiementService paiementService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EtudiantDTO> save(
            @RequestParam MultipartFile image,
            String nomComplet,
            LocalDate dateNaissance
    ) throws IOException {
        log.info(image.getContentType());
        return ResponseEntity.ok(  etudiantService.save(image,  nomComplet,dateNaissance) );
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping(path = "/paiements", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PaiementDTO> newPayement(@RequestParam MultipartFile recu, LocalDate date, double montant, TypeDePaiement typeDePaiement, String codeEtudiant) throws IOException {
        return ResponseEntity.ok(paiementService.save(recu,  date, montant, typeDePaiement, codeEtudiant));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<EtudiantDTO>> getAll(){
     //   log.info(etudiantService.findAll().toString());
        return ResponseEntity.ok(etudiantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtudiantDTO> getEtudiantById(@PathVariable Long id){
        return ResponseEntity.ok(etudiantService.findById(id));
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long  id){
          etudiantService.delete(id);
    }


    @PutMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestParam MultipartFile photo,
                                        EtudiantActionsDTO etudiantDto){
         etudiantService.update(id,photo,etudiantDto);
         return ResponseEntity.noContent().build();
    }

    @GetMapping("/{code}/paiements")
    public ResponseEntity<List<PaiementDTO>> getPaiementByStudentCode(@PathVariable String code){
        return ResponseEntity.ok(paiementService.getAllPaiementByStudentCode(code));
    }



}
