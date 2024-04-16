package com.example.backend.services.impl;

import com.example.backend.dto.EtudiantDTO;
import com.example.backend.entities.Etudiant;
import com.example.backend.mappers.IEtudiantMapper;
import com.example.backend.repo.EtudiantRepository;
import com.example.backend.services.IEtudiantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@AllArgsConstructor
@Service
public class EtudiantService implements IEtudiantService {
    private final EtudiantRepository etudiantRepository;
    private final IEtudiantMapper   mapper;
    private static final String MESSAGENOTFOUND= "Etudiant not found";
    private static   final UUID    uuid = UUID.randomUUID();

    @Override
    public void save(EtudiantDTO etudiantDTO) {
            Etudiant savedEtudiant = mapper.toEntity(etudiantDTO);
            savedEtudiant.setCode(uuid.toString());
            etudiantRepository.save(savedEtudiant);
    }

    @Override
    public EtudiantDTO findById(Long id) {
        Optional<Etudiant> existStudent = etudiantRepository.findById(id);
        return mapper.toDto(
                existStudent.orElseThrow(
                        ()-> new EntityNotFoundException(
                                (MESSAGENOTFOUND)
                        )
                )
                );
    }

    @Override
    public List<EtudiantDTO> findAll() {
        return etudiantRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        if (!etudiantRepository.existsById(id)) {
            throw new EntityNotFoundException((MESSAGENOTFOUND));
        }
        etudiantRepository.deleteById(id);
    }

    @Override
    public EtudiantDTO update(Long id, EtudiantDTO etudiantDTO) {
        boolean etudianExist = etudiantRepository.existsById(id);
        if(!etudianExist){
            throw new EntityNotFoundException((MESSAGENOTFOUND));
        }
        Etudiant etudiant = mapper.toEntity(etudiantDTO);
        etudiant.setId(id);
        return mapper.toDto(
                etudiantRepository.save(etudiant)
        );
    }

    @Override
    public EtudiantDTO findByCode(String code) {
        Optional<Etudiant> existStudent = etudiantRepository.findByCode(code);
        return mapper.toDto(
                existStudent.orElseThrow(
                        ()-> new EntityNotFoundException(
                                (MESSAGENOTFOUND)
                        )
                )
        );
    }
}
