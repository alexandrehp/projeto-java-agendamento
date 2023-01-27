package com.conexa.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public record DadosCadastroMedico(

        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String confirmacaoSenha,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        String cpf,
        @NotBlank
        @Pattern(regexp = "([0-9]{2})/([0-9]{2})/([0-9]{4})")
        String dataNascimento,
        @NotNull
        Especialidade especialidade        ) {
}
