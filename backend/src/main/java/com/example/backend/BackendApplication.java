package com.example.backend;

import com.example.backend.entities.Etudiant;
import com.example.backend.entities.Paiement;
import com.example.backend.entities.TypeDePaiement;
import com.example.backend.repo.EtudiantRepository;
import com.example.backend.repo.PaiementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(EtudiantRepository   etudiantRepository, PaiementRepository paiementRepository) {
        return args -> {
          etudiantRepository.save(Etudiant.builder().id(null).code("112233").nomComplet("Mouhamed Niang").build());
            etudiantRepository.save( Etudiant.builder().id(null).code("102233").nomComplet("Sy Niang").build());
            TypeDePaiement[] typeDePaiements =  TypeDePaiement.values();
            Random random = new Random();
            int index = random.nextInt(typeDePaiements.length);
            paiementRepository.findAll().forEach(
                    p-> {
                        Paiement paiement = null;
                        for (int i = 0; i < 10; i++) {
                            paiement = Paiement
                                    .builder()
                                    .montant(10000 + (int) (Math.random() * 100000))
                                    .date(LocalDate.now())
                                    .type(typeDePaiements[index])
                                    .recu(UUID.randomUUID().toString())
                                    .build();
                        }
                        paiementRepository.save(paiement);
                    }
            );

        };

    }
}
