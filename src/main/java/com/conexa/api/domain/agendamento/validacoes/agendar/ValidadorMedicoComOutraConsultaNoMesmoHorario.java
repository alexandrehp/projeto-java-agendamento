package com.conexa.api.domain.agendamento.validacoes.agendar;

import com.conexa.api.domain.ValidacaoException;
import com.conexa.api.domain.agendamento.AgendamentoRepository;
import com.conexa.api.domain.agendamento.DadosCadastroAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private AgendamentoRepository repository;

    public void validar(DadosCadastroAgendamento dados) {
        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());
        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário");
        }
    }

}
