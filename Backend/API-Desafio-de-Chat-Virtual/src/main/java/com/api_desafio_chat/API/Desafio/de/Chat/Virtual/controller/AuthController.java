package com.api_desafio_chat.API.Desafio.de.Chat.Virtual.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.dto.LoginDTO;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.dto.RegistroDTO;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody LoginDTO login) {
        String token = authService.login(login);
        return ResponseEntity.ok()
            .header("Authorization", "Bearer " + token)
            .header("Access-Control-Expose-Headers", "Authorization")
            .build();
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registro(@RequestBody RegistroDTO novoUsuario) {
        String token = authService.registro(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED)
            .header("Authorization", "Bearer " + token)
            .header("Access-Control-Expose-Headers", "Authorization")
            .body("Usuário registrado com sucesso");
    }
}
