package com.example.backend.controllers;

import com.example.backend.dto.EtudiantDTO;
import com.example.backend.services.IEtudiantService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

@RestController
 @AllArgsConstructor
@Slf4j
public class TestController {

    private final IEtudiantService etudiantService;


}
