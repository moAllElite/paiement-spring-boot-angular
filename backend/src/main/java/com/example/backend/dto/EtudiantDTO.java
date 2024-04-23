package com.example.backend.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;
@Builder
public record EtudiantDTO(
         Long id,
         String code,
         String nomComplet,
         LocalDate dateNaissance,
         String photo
) {
}
