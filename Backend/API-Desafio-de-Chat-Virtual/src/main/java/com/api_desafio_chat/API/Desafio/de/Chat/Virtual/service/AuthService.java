package com.api_desafio_chat.API.Desafio.de.Chat.Virtual.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.dto.LoginDTO;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.dto.RegistroDTO;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.model.Perfil;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.model.Usuario;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.repository.UsuarioRepository;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.security.JWTUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public String login(LoginDTO login) {
        Usuario usuario = usuarioRepository.findByNome(login.getNome())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado")); 
        
        if (!passwordEncoder.matches(login.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        usuario.setOnline(true);
        usuarioRepository.save(usuario);

        return jwtUtil.generateToken(usuario.getNome(), usuario.getPerfil().getAuthority());
    }

    public String registro(RegistroDTO novoUsuario) {
        if (usuarioRepository.findByNome(novoUsuario.getNome()).isPresent()) {
            throw new RuntimeException("Nome de usuário já está em uso");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(novoUsuario.getNome());
        usuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
        usuario.setOnline(true);
        usuario.setPerfil(Perfil.USER); 

        usuarioRepository.save(usuario);

        return jwtUtil.generateToken(usuario.getNome(), usuario.getPerfil().getAuthority());
    }

}
