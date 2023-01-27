package com.conexa.api.domain.logoff;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LogoffRepository extends JpaRepository<Token, Long> {

    Token findByTokenExcluido(String token);
}
