package com.example.backend.controllers;

import com.example.backend.dto.EtudiantDTO;
import com.example.backend.dto.PaiementDTO;
import com.example.backend.services.IEtudiantService;
import com.example.backend.services.IPaiementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("etudiant")
public class EtudiantRestController {
    private final IEtudiantService  etudiantService;
    private final IPaiementService paiementService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody EtudiantDTO etudiantDto){
        etudiantService.save(etudiantDto);
    }

    @GetMapping("/all")
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


}
