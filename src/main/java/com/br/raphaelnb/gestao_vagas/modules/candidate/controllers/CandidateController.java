package com.br.raphaelnb.gestao_vagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.raphaelnb.gestao_vagas.exceptions.UserFoundException;
import com.br.raphaelnb.gestao_vagas.modules.candidate.CandidateEntity;
import com.br.raphaelnb.gestao_vagas.modules.candidate.CandidateRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidate) {
        System.out.println("email do candidato: " + candidate.getEmail());
        this.candidateRepository
                .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        ;

        return candidateRepository.save(candidate);
    }

}
