package com.example.backend.dto;

import lombok.Builder;

import java.util.Date;
@Builder
public record EtudiantDTO(
         Long id,
         String code,
         String nomComplet,
         Date dateNaissance,
         String photo
) {
}
