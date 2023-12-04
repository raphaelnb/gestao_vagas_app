package com.br.raphaelnb.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.raphaelnb.gestao_vagas.exceptions.UserFoundException;
import com.br.raphaelnb.gestao_vagas.modules.company.entities.CompanyEntity;
import com.br.raphaelnb.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class createCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {

        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return this.companyRepository.save(companyEntity);
    }
}
