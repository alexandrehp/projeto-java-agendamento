package com.conexa.api.domain.agendamento.validacoes.agendar;

import com.conexa.api.domain.ValidacaoException;
import com.conexa.api.domain.agendamento.AgendamentoRepository;
import com.conexa.api.domain.agendamento.DadosCadastroAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private AgendamentoRepository repository;

    public void validar(DadosCadastroAgendamento dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }

}
