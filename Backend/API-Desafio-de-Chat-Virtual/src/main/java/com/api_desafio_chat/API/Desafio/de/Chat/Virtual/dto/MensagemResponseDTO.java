package com.api_desafio_chat.API.Desafio.de.Chat.Virtual.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MensagemResponseDTO {
    
    private Long id;
    private String remetenteNome;
    private String destinatarioNome;
    private String conteudo;
    private LocalDateTime dataEnvio;
}
