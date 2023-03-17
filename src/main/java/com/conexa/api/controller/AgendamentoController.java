package com.conexa.api.controller;

import com.conexa.api.domain.agendamento.AgendamentoService;
import com.conexa.api.domain.agendamento.DadosCadastroAgendamento;
import com.conexa.api.domain.logoff.LogoffService;
import com.conexa.api.infra.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/attendance")
@SecurityRequirement(name = "bearer-key")
public class AgendamentoController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private LogoffService logoffService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroAgendamento dados, @RequestHeader HttpHeaders header, UriComponentsBuilder uriBuilder) {

        String tokenJWT = header.get("Authorization").get(0).replace("Bearer ", "");

        if (logoffService.verificarTokenExcluido(tokenJWT)) {
            return ResponseEntity.badRequest().body("Token JWT inválido ou expirado.");
        }

        var subject = tokenService.getSubject(tokenJWT);

        var dto = agendamentoService.agendar(dados);

        var uri = uriBuilder.path("/attendance/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body("ok");

    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var agendamento = agendamentoService.getReferenceById(id);
        return ResponseEntity.ok(agendamento);
    }
}