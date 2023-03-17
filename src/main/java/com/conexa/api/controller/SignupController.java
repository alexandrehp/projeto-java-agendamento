package com.conexa.api.controller;

import com.conexa.api.domain.medico.DadosCadastroMedico;
import com.conexa.api.domain.medico.DadosDetalhamentoMedico;
import com.conexa.api.domain.medico.Medico;
import com.conexa.api.domain.medico.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private MedicoRepository medicoService;

    @PostMapping
    @Transactional
    public ResponseEntity signup(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {

        try {
            var medico = new Medico(dados);
            medicoService.save(medico);
            var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

}
