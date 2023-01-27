package com.conexa.api.controller;

import com.conexa.api.domain.medico.DadosCadastroMedico;
import com.conexa.api.domain.medico.DadosDetalhamentoMedico;
import com.conexa.api.domain.medico.MedicoService;
import com.conexa.api.infra.util.CpfUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    @Transactional
    public ResponseEntity signup(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {

        if (!dados.senha().equals(dados.confirmacaoSenha())) {
            return ResponseEntity.badRequest().body( "As senhas não são iguais!");
        }

        if (!CpfUtil.validarCpf(dados.cpf().replaceAll("[^0-9]+", ""))) {
            return ResponseEntity.badRequest().body("CPF incorreto!");
        }

        try {
            var medico =  medicoService.save(dados);
            var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
            return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));

        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

}
