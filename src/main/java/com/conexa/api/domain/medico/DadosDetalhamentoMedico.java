package com.conexa.api.domain.medico;


public record DadosDetalhamentoMedico(Long id, String email, String cpf, String telefone, Especialidade especialidade) {

    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getEmail(), medico.getCpf(), medico.getTelefone(), medico.getEspecialidade());
    }
}
