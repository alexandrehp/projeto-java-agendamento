package com.conexa.api.domain.medico;

import com.conexa.api.infra.util.CpfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico getReferenceById(Long id) {
        return medicoRepository.getReferenceById(id);
    }

    public Medico findByEmail(String email){
        return medicoRepository.findByEmail(email);
    }

    public Medico save(DadosCadastroMedico dados) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Medico medico = new Medico();
        medico.setEmail(dados.email());
        medico.setSenha(encoder.encode(dados.senha()));
        medico.setDataNascimento(LocalDate.parse(dados.dataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        medico.setTelefone(dados.telefone());
        medico.setCpf(dados.cpf().replaceAll("[^0-9]+", ""));
        medico.setEspecialidade(dados.especialidade());

        return medicoRepository.save(medico);
    }


}
