package com.conexa.api.domain.agendamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record Paciente (
    @NotBlank
    String nome,
    @NotBlank
    String cpf) {

}