package com.api_desafio_chat.API.Desafio.de.Chat.Virtual.repository;

import org.springframework.stereotype.Repository;

import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.model.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNome(String nome);
    
    List<Usuario> findByOnlineTrue();
}
