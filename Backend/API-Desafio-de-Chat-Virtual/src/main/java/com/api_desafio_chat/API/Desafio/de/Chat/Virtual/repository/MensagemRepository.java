package com.api_desafio_chat.API.Desafio.de.Chat.Virtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.model.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {

    @Query("SELECT m FROM Mensagem m " +
            "INNER JOIN m.remetente r " +
            "INNER JOIN m.destinatario d " +
            "WHERE (r.nome = :usuario1 AND d.nome = :usuario2) " +
            "   OR (r.nome = :usuario2 AND d.nome = :usuario1) " +
            "ORDER BY m.dataEnvio ASC")
    List<Mensagem> buscarConversasEntre(String usuario1, String usuario2);
}
