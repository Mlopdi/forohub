package com.alura.forohub.dto;

import com.alura.forohub.model.Perfil;

public record DatosSalidaPerfil(Long id, String nombre) {
    public DatosSalidaPerfil(Perfil perfil) {
        this(perfil.getId(), perfil.getNombre());
    }
}
