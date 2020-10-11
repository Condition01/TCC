package br.com.ifeira.auth.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    CLIENTE,
    FEIRANTE,
    ENTREGADOR;

    @Override
    public String getAuthority() {
        return this.name();
    }
}