package com.br.raphaelnb.gestao_vagas.modules.candidate.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.raphaelnb.gestao_vagas.exceptions.UserFoundException;
import com.br.raphaelnb.gestao_vagas.modules.candidate.CandidateEntity;
import com.br.raphaelnb.gestao_vagas.modules.candidate.CandidateRepository;
import com.br.raphaelnb.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        try {
            System.out.println("email do candidato: " + candidate.getEmail());
            var result = this.createCandidateUseCase.execute(candidate);
            return ResponseEntity.ok().body(result);
        } catch (Exception error) {
            return ResponseEntity.badRequest().body(error.getMessage());
        }

    }

}
