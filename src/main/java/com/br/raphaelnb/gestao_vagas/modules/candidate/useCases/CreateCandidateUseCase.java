package com.br.raphaelnb.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.raphaelnb.gestao_vagas.exceptions.UserFoundException;
import com.br.raphaelnb.gestao_vagas.modules.candidate.CandidateEntity;
import com.br.raphaelnb.gestao_vagas.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        ;

        return candidateRepository.save(candidateEntity);
    }
}
