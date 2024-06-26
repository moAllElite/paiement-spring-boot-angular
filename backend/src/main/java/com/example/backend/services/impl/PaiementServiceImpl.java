package com.example.backend.services.impl;

import com.example.backend.dto.PaiementDTO;
import com.example.backend.entities.EtatDePaiement;
import com.example.backend.entities.Etudiant;
import com.example.backend.entities.Paiement;
import com.example.backend.entities.TypeDePaiement;
import com.example.backend.mappers.IPaiementMapper;
import com.example.backend.repo.EtudiantRepository;
import com.example.backend.repo.PaiementRepository;
import com.example.backend.services.IPaiementService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@Transactional
@Service
@AllArgsConstructor
public class PaiementServiceImpl implements IPaiementService {
    private static final String SOUS_DOSSIER ="paiement";
    private static final String REPERTOIRE_PARENT="dossier-etudiant-reçus";
    private static final String NOTFOUNDMESSAGE="Aucun paiement n'a été trouvé avec l'id %s";
    private final PaiementRepository paiementRepository;
    private final EtudiantRepository etudiantRepository;
    private final IPaiementMapper paiementMapper;

    @Override
    public List<PaiementDTO> getAllPaiementByStudentCode(String code) {
        return paiementRepository.findAllByEtudiantCode(code)
                .stream().map(paiementMapper::toDto)
                .toList();
    }

    @Override
    public List<PaiementDTO> getAllPaiementByStatus(EtatDePaiement etatDePaiement) {
        return paiementRepository.findAllByEtat(etatDePaiement)
                .stream().map(paiementMapper::toDto)
                .toList();
    }


    @Override
    public PaiementDTO save(
            MultipartFile recu,
            LocalDate date,
            double montant,
            TypeDePaiement typeDePaiement,
            String codeEtudiant
    ) throws IOException {

        Path path = Paths.get(System.getProperty("user.home"),REPERTOIRE_PARENT, SOUS_DOSSIER);

        if(!Files.exists(path)){
            Files.createDirectories(path);
        }
        String fileId = UUID.randomUUID().toString();
        String extension = "";
        List<String> fileProperties = Arrays.stream(Objects.requireNonNull(recu.getContentType()).split("/")).toList();
        if(fileProperties.contains("pdf")){
            extension = ".pdf";
        }else if(fileProperties.contains("txt")){
            extension = ".txt";
        }else if(fileProperties.contains("csv")){
            extension = ".csv";
        }


        Path filePath = Paths.get(
                System.getProperty("user.home"),
                REPERTOIRE_PARENT,SOUS_DOSSIER ,//chemin du repértoire
                SOUS_DOSSIER +fileId+extension
        );
        Files.copy(recu.getInputStream(),filePath);

        Etudiant etudiant = etudiantRepository.findByCode(codeEtudiant)
                .orElseThrow(EntityNotFoundException::new);

        Paiement paiement= Paiement.builder()
                .etudiant(etudiant)
                .type(typeDePaiement)
                .montant(montant)
                .date(date)
                .etat(EtatDePaiement.CREE)
                .recu(filePath.toUri().toString())
                .build();

        return  paiementMapper.toDto(paiementRepository.save(paiement));
    }

    @Override
    public byte[] getPaiementFileById(Long paiementId) throws IOException {
        PaiementDTO paiementDTO = paiementMapper.toDto(
                paiementRepository.findById(paiementId)
                        .orElseThrow(()-> new EntityNotFoundException(String.format(NOTFOUNDMESSAGE, paiementId)))
        );
        String filePath = paiementDTO.recu();
        return Files.readAllBytes(Path.of(URI.create(filePath))); // type of byte[]

    }

    @Override
    public PaiementDTO findById(Long id) {
        return paiementRepository.findById(id)
                .map(paiementMapper::toDto)
                .orElseThrow(
                        ()-> new EntityNotFoundException(String.format(NOTFOUNDMESSAGE, id )
                        ));
    }

    @Override
    public void delete(Long id) {
        if (!paiementRepository.existsById(id)) {
            throw  new EntityNotFoundException(String.format(NOTFOUNDMESSAGE,id));
        }
        paiementRepository.deleteById(id);
    }

    @Override
    public List<PaiementDTO> findAll() {
        return paiementRepository.findAll()
                .stream()
                .map(paiementMapper::toDto)
                .toList();
    }

    @Override
    public PaiementDTO update(Long id,EtatDePaiement etatDePaiement) {
        Paiement paiement = paiementRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException(String.format(NOTFOUNDMESSAGE,id))
        );
        paiement.setEtat(etatDePaiement);
        paiementRepository.save(paiement);
        return paiementMapper.toDto(
                paiementRepository.save(paiement)
        );
    }


}
