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

import static java.lang.Math.random;

@SpringBootApplication
public class BackendApplication {
    private final Random random = new Random();
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(EtudiantRepository   etudiantRepository, PaiementRepository paiementRepository) {
        return args -> {
          etudiantRepository.save(Etudiant.builder().id(null).code("193233").nomComplet("Mouhamed Niang").build());
            etudiantRepository.save( Etudiant.builder().id(null).code("100233").nomComplet("Sy Niang").build());
            etudiantRepository.save( Etudiant.builder().id(null).code("202233").nomComplet("Dial Niang").build());
            etudiantRepository.save( Etudiant.builder().id(null).code("702233").nomComplet("diagne Niang").build());
            etudiantRepository.save( Etudiant.builder().id(null).code("502233").nomComplet("tall Niang").build());
            etudiantRepository.save( Etudiant.builder().id(null).code("152233").nomComplet("fall Niang").build());
            etudiantRepository.save( Etudiant.builder().id(null).code("115233").nomComplet("fam Niang").build());
            TypeDePaiement[] typeDePaiements =  TypeDePaiement.values();

            etudiantRepository.findAll().forEach(
                    student-> {
                        Paiement paiement = null;
                        for (int i = 0; i < 100; i++) {
                            int index = this.random.nextInt(typeDePaiements.length);

                            int randomValue = random.nextInt();
                            double saveMontant = (10000 + randomValue * 100000);
                            paiement = Paiement
                                    .builder()
                                    .montant(saveMontant)
                                    .date(LocalDate.now())
                                    .type(typeDePaiements[index])
                                    .date(LocalDate.now())
                                    .etudiant(student)
                                    .recu(UUID.randomUUID().toString())
                                    .build();
                        }
                        paiementRepository.save(paiement);
                    }
            );

        };

    }
}
