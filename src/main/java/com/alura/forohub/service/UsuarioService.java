package com.alura.forohub.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alura.forohub.model.Usuario;
import com.alura.forohub.repository.UsuarioRepository;

import jakarta.validation.constraints.NotNull;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarAutor(@NotNull Long id) {
        Optional<Usuario> autor = usuarioRepository.findById(id);
        if (autor.isPresent()){
            return autor.get();
        } else {
            throw new RuntimeException("Autor no encontrado.");
        }
    }

}
