package com.alura.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistraCurso(Long id, @NotBlank String nombre, @NotBlank String categoria) {}
