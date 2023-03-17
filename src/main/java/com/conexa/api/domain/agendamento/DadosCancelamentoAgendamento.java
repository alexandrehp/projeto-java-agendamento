package com.conexa.api.domain.agendamento;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoAgendamento(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo) {
}