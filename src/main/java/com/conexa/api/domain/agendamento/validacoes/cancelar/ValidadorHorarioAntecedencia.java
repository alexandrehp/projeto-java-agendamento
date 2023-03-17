package com.conexa.api.domain.agendamento.validacoes.cancelar;

import com.conexa.api.domain.ValidacaoException;
import com.conexa.api.domain.agendamento.AgendamentoRepository;
import com.conexa.api.domain.agendamento.DadosCancelamentoAgendamento;
import com.conexa.api.domain.agendamento.validacoes.cancelar.ValidadorCancelamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {

    @Autowired
    private AgendamentoRepository repository;

    @Override
    public void validar(DadosCancelamentoAgendamento dados) {
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}
