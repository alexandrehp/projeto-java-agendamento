package com.conexa.api.domain.logoff;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogoffService {

    @Autowired
    private LogoffRepository logoffRepository;

    public Token save(String token) {
        Token tokenExcluido = new Token();
        tokenExcluido.setTokenExcluido(token);
        return logoffRepository.save(tokenExcluido);
    }

    public Boolean verificarTokenExcluido(String token) {
        Token tokenExcluido = logoffRepository.findByTokenExcluido(token);

        if (tokenExcluido == null) {
            return false;
        } else {
            return true;
        }
    }
}
