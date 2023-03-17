package com.conexa.api.domain.agendamento.validacoes.agendar;


import com.conexa.api.domain.ValidacaoException;
import com.conexa.api.domain.agendamento.DadosCadastroAgendamento;
import com.conexa.api.domain.agendamento.validacoes.agendar.ValidadorAgendamentoDeConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosCadastroAgendamento dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }

    }
}
