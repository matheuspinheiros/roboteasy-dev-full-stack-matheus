package com.api_desafio_chat.API.Desafio.de.Chat.Virtual.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.dto.MensagemRequestDTO;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.dto.MensagemResponseDTO;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.model.Mensagem;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.model.Usuario;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.repository.MensagemRepository;
import com.api_desafio_chat.API.Desafio.de.Chat.Virtual.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MensagemService {
    
    private final UsuarioRepository usuarioRepository;
    private final MensagemRepository mensagemRepository;

     public MensagemResponseDTO enviarMensagem(String remetenteNome, MensagemRequestDTO dto) {
        Usuario remetente = usuarioRepository.findByNome(remetenteNome)
                .orElseThrow(() -> new RuntimeException("Remetente não encontrado"));

        Usuario destinatario = usuarioRepository.findByNome(dto.getDestinatarioNome())
                .orElseThrow(() -> new RuntimeException("Destinatário não encontrado"));

        Mensagem mensagem = new Mensagem();
        mensagem.setRemetente(remetente);
        mensagem.setDestinatario(destinatario);
        mensagem.setConteudo(dto.getConteudo());
        mensagem.setDataEnvio(LocalDateTime.now());

        mensagemRepository.save(mensagem);

        return new MensagemResponseDTO(
                mensagem.getId(),
                remetente.getNome(),
                destinatario.getNome(),
                mensagem.getConteudo(),
                mensagem.getDataEnvio()
        );
    }

    public List<MensagemResponseDTO> listarConversas(String usuario1, String usuario2) {
        return mensagemRepository.buscarConversasEntre(usuario1, usuario2)
            .stream()
            .map(m -> new MensagemResponseDTO(
                m.getId(),
                m.getRemetente().getNome(),
                m.getDestinatario().getNome(),
                m.getConteudo(),
                m.getDataEnvio()))
            .collect(Collectors.toList());
    }
}
