package com.api_desafio_chat.API.Desafio.de.Chat.Virtual.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MensagemRequestDTO {

    private String destinatarioNome;
    private String conteudo;
}
