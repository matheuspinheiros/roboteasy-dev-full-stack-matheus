package com.api_desafio_chat.API.Desafio.de.Chat.Virtual.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Perfil {
    USER(1, "ROLE_USER"),
    ADMIN(2, "ROLE_ADMIN");

    private final int cod;
    private final String authority;


    public String getAuthority() {
        return authority;
    }
}
