package com.conexa.api.domain.agendamento;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DadosCadastroAgendamento(

        @NotBlank
        String dataHora,

        @NotNull @Valid Paciente paciente      ) {
}
