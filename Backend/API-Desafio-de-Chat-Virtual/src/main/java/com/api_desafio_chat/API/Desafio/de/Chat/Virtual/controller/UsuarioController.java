package com.api_desafio_chat.API.Desafio.de.Chat.Virtual.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.dto.UsuarioResponseDTO;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        usuarioService.logout(request);
        return ResponseEntity.ok("Logout realizado com sucesso.");
    }

    @GetMapping("/online")
    public ResponseEntity<List<UsuarioResponseDTO>> getUsuariosOnline() {
        List<UsuarioResponseDTO> online = usuarioService.listarUsuariosOnline();
        return ResponseEntity.ok(online);
    }

}
