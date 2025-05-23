package com.api_desafio_chat.API.Desafio.de.Chat.Virtual.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.dto.MensagemRequestDTO;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.dto.MensagemResponseDTO;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.service.MensagemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MensagemController {
    
    private final SimpMessagingTemplate messagingTemplate;
    private final MensagemService mensagemService;

    @MessageMapping("/chat/enviar")
    public void enviarMensagem(MensagemRequestDTO dto, Principal principal) {
        String remetenteNome = principal.getName();
        MensagemResponseDTO response = mensagemService.enviarMensagem(remetenteNome, dto);

        //envia a mensagem para canal destinatario
        messagingTemplate.convertAndSendToUser(
            dto.getDestinatarioNome(), "/topic/mensagens", response
        );

        //tambem envia ao remetente autoatualização do chat
        messagingTemplate.convertAndSendToUser(
            remetenteNome, "/topic/mensagens", response
        );
    }

    @GetMapping("/api/conversas")
    public ResponseEntity<List<MensagemResponseDTO>> listarMensagens(
        @RequestParam String usuario1,
        @RequestParam String usuario2
    ) {
        List<MensagemResponseDTO> mensagens = mensagemService.listarConversas(usuario1, usuario2);
        return ResponseEntity.ok(mensagens);
    }
}
