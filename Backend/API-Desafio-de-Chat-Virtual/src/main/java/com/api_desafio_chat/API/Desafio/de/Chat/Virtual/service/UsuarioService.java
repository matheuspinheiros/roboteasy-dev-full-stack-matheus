package com.api_desafio_chat.API.Desafio.de.Chat.Virtual.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.dto.UsuarioResponseDTO;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.model.Usuario;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.repository.UsuarioRepository;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.security.JWTUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final JWTUtil jwtUtil;

    public void logout(HttpServletRequest request) {
        String nome = jwtUtil.getUsernameFromRequest(request);
        Usuario usuario = usuarioRepository.findByNome(nome)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setOnline(false);
        usuarioRepository.save(usuario);
    }

    public List<UsuarioResponseDTO> listarUsuariosOnline() {
        return usuarioRepository.findByOnlineTrue().stream()
                .map(x -> new UsuarioResponseDTO(x.getId(), x.getNome(), x.isOnline())) // lambda
                .toList();
    }

}
