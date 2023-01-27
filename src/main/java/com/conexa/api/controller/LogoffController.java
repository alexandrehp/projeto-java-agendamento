package com.conexa.api.controller;

import com.conexa.api.domain.agendamento.DadosCadastroAgendamento;
import com.conexa.api.domain.logoff.LogoffService;
import com.conexa.api.domain.medico.Medico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/logoff")
public class LogoffController {

    @Autowired
    private LogoffService logoffService;

    @PostMapping
    public ResponseEntity logoff(@RequestHeader HttpHeaders header) {

        String tokenJWT = header.get("Authorization").get(0).replace("Bearer ", "");

        logoffService.save(tokenJWT);

        return ResponseEntity.noContent().build();

    }
}
