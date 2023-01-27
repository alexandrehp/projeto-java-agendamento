package com.conexa.api.domain.agendamento;


import com.conexa.api.domain.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public Agendamento getReferenceById(Long id) {
        return agendamentoRepository.getReferenceById(id);
    }

    public Agendamento save(DadosCadastroAgendamento dados, Medico medico) {
        Agendamento agendamento = new Agendamento();
        agendamento.setDataHora(LocalDate.parse(dados.dataHora(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        agendamento.setNome(dados.paciente().nome());
        agendamento.setCpf(dados.paciente().cpf());
        agendamento.setMedico(medico);

        return agendamentoRepository.save(agendamento);
    }
}
