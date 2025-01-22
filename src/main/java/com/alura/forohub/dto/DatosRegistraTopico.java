package com.alura.forohub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistraTopico(
    Long id,
    @NotBlank String titulo,
    @NotBlank String mensaje,
    @NotNull Long idAutor,
    @NotNull Long idCurso
) {}
