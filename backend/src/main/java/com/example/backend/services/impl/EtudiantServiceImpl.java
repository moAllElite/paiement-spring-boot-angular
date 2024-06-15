package com.example.backend.services.impl;

import com.example.backend.dto.EtudiantDTO;
import com.example.backend.dto.EtudiantActionsDTO;
import com.example.backend.entities.Etudiant;
import com.example.backend.mappers.IEtudiantMapper;
import com.example.backend.repo.EtudiantRepository;
import com.example.backend.services.IEtudiantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@Service
@Slf4j
public class EtudiantServiceImpl implements IEtudiantService {
    private final EtudiantRepository etudiantRepository;
    private final IEtudiantMapper   mapper;
    private final Random random = new Random();
    private static final String MESSAGENOTFOUND= "Etudiant not found";
    private static final String SOUS_DOSSIER ="images";
    private static final String REPERTOIRE_PARENT="dossier-etudiant-reçus";
    @Override
    public EtudiantDTO save(MultipartFile image,  String nomComplet, LocalDate dateNaissance)  throws IOException {
            // upload image
        Path path = Paths.get(System.getProperty("user.home"),REPERTOIRE_PARENT, SOUS_DOSSIER);
        //on vérifie si le répertoire existe sinon
        //on le crée
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }

        String imageId = UUID.randomUUID().toString();
        List<String> fileProperties= Arrays.stream(Objects.requireNonNull(image.getContentType()).split("/")).toList();
        String extension;
        if(fileProperties.contains("jpeg")){
            extension = ".jpg";
        }else if(fileProperties.contains("png")){
            extension = ".png";
        }else if(fileProperties.contains("gif")){
            extension = ".gif";
        }else{
            extension = ".pdf";
        }
        int code = random.nextInt(999999);
        Path imagePath = Paths.get(
                System.getProperty("user.home"),
                REPERTOIRE_PARENT,SOUS_DOSSIER ,//chemin du repértoire
                 "photo" + imageId + extension);
        Files.copy(image.getInputStream(),imagePath);
        Etudiant etudiant= Etudiant.builder()
                .nomComplet(nomComplet)
                .code(String.format("%06d",code))
                .dateNaissance(dateNaissance)
                .photo(imagePath.toUri().toString())
                .build();

        Etudiant newEtudiant = etudiantRepository.save(etudiant);
        log.info(imagePath.toUri().toString());
        return mapper.toDto(newEtudiant);
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
    public EtudiantDTO update(Long id, MultipartFile photo, EtudiantActionsDTO etudiantActionsDTO) {
        boolean etudianExist = etudiantRepository.existsById(id);
        if(!etudianExist){
            throw new EntityNotFoundException((MESSAGENOTFOUND));
        }
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException((MESSAGENOTFOUND)));
        EtudiantDTO etudiantDTO = EtudiantDTO.builder()
                .code(etudiant.getCode())
                .id(id)
                .nomComplet(etudiantActionsDTO.getNomComplet())
                .dateNaissance(etudiantActionsDTO.getDateNaissance())
                .build();
        Etudiant savedEtudiant = mapper.toEntity(etudiantDTO);


        String imageId = UUID.randomUUID().toString();
        String extension="";
        List<String> fileProperties = Arrays.stream(Objects.requireNonNull(photo.getContentType()).split("/")).toList();
        if (fileProperties.contains("jpg")) {
            extension = ".jpg";
        } else if (fileProperties.contains("png")) {
            extension = ".png";
        }else if(fileProperties.contains("gif")){
            extension = ".gif";
        }else if(fileProperties.contains("jpeg")){
            extension = ".jpeg";
        }
        Path imagePath = Paths.get(
                System.getProperty("user.home"),
                REPERTOIRE_PARENT,SOUS_DOSSIER ,//chemin du repértoire
                "photo" + imageId + extension);
        savedEtudiant.setPhoto("");

            return mapper.toDto(
                etudiantRepository.save(savedEtudiant)
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
