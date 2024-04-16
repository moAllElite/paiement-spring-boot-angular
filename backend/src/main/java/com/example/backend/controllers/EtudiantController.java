package com.example.backend.controllers;

import com.example.backend.dto.EtudiantDTO;
import com.example.backend.services.IEtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("etudiant")
public class EtudiantController {
    private final IEtudiantService  etudiantService;
    @PostMapping
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
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long  id){
          etudiantService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EtudiantDTO> update(@PathVariable Long id, @RequestBody EtudiantDTO etudiantDto){
          return   ResponseEntity.ok(etudiantService.update(id, etudiantDto));
    }
}
