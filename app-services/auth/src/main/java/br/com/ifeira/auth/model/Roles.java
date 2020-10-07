package br.com.ifeira.auth.model;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_FEIRANTE,
    ROLE_ENTREGADOR;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
