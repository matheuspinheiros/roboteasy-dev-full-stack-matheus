package com.api_desafio_chat.API.Desafio.de.Chat.Virtual.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.model.Usuario;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.repository.UsuarioRepository;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNome(nome)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + nome));
        
        return new UserSpringSecurity(
                usuario.getId(),
                usuario.getNome(),
                usuario.getSenha(),
                usuario.getPerfil());
    }
}
