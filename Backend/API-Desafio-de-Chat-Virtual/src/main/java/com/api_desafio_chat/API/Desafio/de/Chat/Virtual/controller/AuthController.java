package com.api_desafio_chat.API.Desafio.de.Chat.Virtual.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.dto.LoginDTO;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.dto.RegistroDTO;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.model.Perfil;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.model.Usuario;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.repository.UsuarioRepository;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.security.JWTUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody LoginDTO login) {
        Usuario usuario = usuarioRepository.findByNome(login.getNome())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        if (!passwordEncoder.matches(login.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        usuario.setOnline(true);
        usuarioRepository.save(usuario);

        String token = jwtUtil.generateToken(usuario.getNome(), usuario.getPerfil().getAuthority());
        return ResponseEntity.ok()
            .header("Authorization", "Bearer " + token)
            .header("Acces-Control-Expose-Headers", "Authorization")
            .build();
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registro(@RequestBody RegistroDTO novoUsuario) {
        if (usuarioRepository.findByNome(novoUsuario.getNome()).isPresent()) {
            return ResponseEntity.badRequest().body("Nome de usuário já está em uso");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(novoUsuario.getNome());
        usuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));
        usuario.setOnline(true);
        usuario.setPerfil(Perfil.USER); 

        usuarioRepository.save(usuario);

        String token = jwtUtil.generateToken(usuario.getNome(), usuario.getPerfil().getAuthority());

        return ResponseEntity.status(HttpStatus.CREATED)
            .header("Authorization", "Bearer " + token)
            .header("Access-Control-Expose-Headers", "Authorization")
            .body("Usuário registrado com sucesso");
    }


}
