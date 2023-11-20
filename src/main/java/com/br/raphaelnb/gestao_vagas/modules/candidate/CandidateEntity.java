package com.br.raphaelnb.gestao_vagas.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {

    private UUID id;
    private String name;

    @Pattern(regexp = "\\S+", message = "Não pode ter espaço")
    private String username;

    @Email(message = "Insira um email válido")
    private String email;

    @Length(min = 8, max = 18, message = "A senha deve ter entre 8 e 18 caracteres")
    private String password;
    private String description;
    private String curriculum;
}
