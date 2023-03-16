package com.conexa.api.domain.medico;

public record DadosListagemMedico(Long id,  String email, String cpf, Especialidade especialidade) {

    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
