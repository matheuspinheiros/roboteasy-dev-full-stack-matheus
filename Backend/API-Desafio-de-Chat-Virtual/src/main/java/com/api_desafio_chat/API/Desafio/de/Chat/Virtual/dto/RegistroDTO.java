package com.api_desafio_chat.API.Desafio.de.Chat.Virtual.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class RegistroDTO {
    
    private String nome;
    private String senha;
}
