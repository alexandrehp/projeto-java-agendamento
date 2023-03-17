package com.conexa.api.domain.agendamento;


import com.conexa.api.domain.ValidacaoException;
import com.conexa.api.domain.agendamento.validacoes.agendar.ValidadorAgendamentoDeConsulta;
import com.conexa.api.domain.agendamento.validacoes.cancelar.ValidadorCancelamentoDeConsulta;
import com.conexa.api.domain.medico.Medico;
import com.conexa.api.domain.medico.MedicoRepository;
import com.conexa.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public Agendamento getReferenceById(Long id) {
        return agendamentoRepository.getReferenceById(id);
    }

    public DadosDetalhamentoAgendamento agendar(DadosCadastroAgendamento dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data!");
        }

        var agendamento = new Agendamento(null, medico, paciente, dados.data(), null);
        agendamentoRepository.save(agendamento);

        return new DadosDetalhamentoAgendamento(agendamento);
    }

    public void cancelar(DadosCancelamentoAgendamento dados) {
        if (!agendamentoRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = agendamentoRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }


    private Medico escolherMedico(DadosCadastroAgendamento dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
