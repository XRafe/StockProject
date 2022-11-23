package org.project.stock.security.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthenticationImpl implements Authentication {

    private boolean statusAuth;
    private String login;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return login;
    }

    @Override
    public boolean isAuthenticated() {
        return statusAuth;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.statusAuth = isAuthenticated;
    }

    @Override
    public String getName() {
        return null;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
