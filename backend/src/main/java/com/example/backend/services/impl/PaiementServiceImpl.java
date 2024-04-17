package com.example.backend.services.impl;

import com.example.backend.dto.PaiementDTO;
import com.example.backend.entities.EtatDePaiement;
import com.example.backend.entities.Paiement;
import com.example.backend.mappers.IPaiementMapper;
import com.example.backend.repo.PaiementRepository;
import com.example.backend.services.IPaiementService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PaiementServiceImpl implements IPaiementService {
    private static final String RACINE_DU_FICHIER ="paiement";
    private static final String REPERTOIRE_PARENT="dossier-etudiant-reçus";
    private static final String NOTFOUNDMESSAGE="Aucun paiement n'a été trouvé avec l'id %s";
    private final PaiementRepository paiementRepository;
    private final IPaiementMapper paiementMapper;
    @Override
    public List<PaiementDTO> getAllPaiementByStudentCode(String code) {
        return paiementRepository.findAllByEtudiantCode(code)
                .stream().map(paiementMapper::toDto)
                .toList();
    }

    @Override
    public List<PaiementDTO> getAllPaiementByEtat(EtatDePaiement etatDePaiement) {
        return paiementRepository.findAllByEtat(etatDePaiement)
                .stream().map(paiementMapper::toDto)
                .toList();
    }


    @Override
    public PaiementDTO save(MultipartFile recu, PaiementDTO paiementDTO) throws IOException {
        Path path = Paths.get(System.getProperty("user.home"),REPERTOIRE_PARENT, RACINE_DU_FICHIER);
        /*
          On vérifie si la répertoire qui
           doit contrenir les fichiers  de paie existe sinon on le crée
         */
        if(Files.exists(path)){
            Files.createDirectory(path);
        }
        String fileId = UUID.randomUUID().toString();
        Path filePath = Paths.get(
                System.getProperty("user.home"), REPERTOIRE_PARENT,//chemin du repértoire
                RACINE_DU_FICHIER+fileId+".pdf"
        );
       Files.copy(recu.getInputStream(),filePath);
       Paiement paiement = paiementMapper.toEntity(paiementDTO);
       paiement.setRecu(filePath.toUri().toString());
        return  paiementMapper.toDto(paiementRepository.save(paiement));
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
    public List<PaiementDTO> findAll() {
        return List.of();
    }

    @Override
    public void delete(Long id) {
        if (!paiementRepository.existsById(id)) {
            throw  new EntityNotFoundException(String.format(NOTFOUNDMESSAGE,id));
        }
        paiementRepository.deleteById(id);
    }

    @Override
    public PaiementDTO update(Long id, PaiementDTO paiementDTO) {
        if (!paiementRepository.existsById(id)) {
            throw  new EntityNotFoundException(String.format(NOTFOUNDMESSAGE,id));
        }
        Paiement entity = paiementMapper.toEntity(paiementDTO);
        entity.setId(id);
        paiementRepository.save(entity);
        return paiementMapper.toDto(
                paiementRepository.save(entity)
        );
    }
}
