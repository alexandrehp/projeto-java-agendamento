package com.conexa.api.domain.autenticacao;


import com.conexa.api.domain.medico.Medico;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AutenticacaoDetails implements UserDetails {

    private final Medico medico;

    public AutenticacaoDetails(Medico medico) {
        this.medico = medico;
    }

    public Medico getMedico() {
        return medico;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return medico.getSenha();
    }

    @Override
    public String getUsername() {
        return medico.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
