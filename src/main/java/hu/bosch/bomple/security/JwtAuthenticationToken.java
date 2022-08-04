package hu.bosch.bomple.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class JwtAuthenticationToken implements Authentication {

    private final String jwt;
    private final Long userId;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean authenticated;

    public JwtAuthenticationToken(String jwt) {
        this.jwt = jwt;
        this.authenticated = false;
        this.userId = null;
        this.authorities = Collections.emptyList();
    }

    public JwtAuthenticationToken(String jwt, Long userId,
            List<SimpleGrantedAuthority> authorities) {
        this.jwt = jwt;
        this.userId = userId;
        this.authorities = authorities;
        this.authenticated = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return jwt;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("Dont do dis");
    }

    @Override
    public String getName() {
        return userId.toString();
    }
}
