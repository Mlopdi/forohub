package com.alura.forohub.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alura.forohub.model.Topico;
import com.alura.forohub.repository.TopicoRepository;

@Service
public class TopicoService {
    private TopicoRepository topicoRepository;

    public TopicoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    public void validarTopico(String titulo, String mensaje) {
        Optional<Topico> existente = topicoRepository.findByTituloAndMensaje(titulo, mensaje);
        if (existente.isPresent()) {
            throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje.");
        }
    }
}
