package com.alura.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistraPerfil(@NotBlank String nombre) {}
