package com.api_desafio_chat.API.Desafio.de.Chat.Virtual.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.model.Perfil;

import lombok.Getter;

@Getter
public class UserSpringSecurity implements UserDetails {
    private static final long serialVersionUID = 1L;
    
    private final Long id;
    private final String nome;
    private final String senha;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserSpringSecurity(Long id, String nome, String senha, Perfil perfil) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.authorities = Collections.singleton(new SimpleGrantedAuthority(perfil.getAuthority()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nome;
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
