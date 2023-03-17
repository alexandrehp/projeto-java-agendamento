package com.conexa.api.domain.agendamento;

import java.time.LocalDateTime;

public record DadosDetalhamentoAgendamento(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {
    public DadosDetalhamentoAgendamento(Agendamento consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}


