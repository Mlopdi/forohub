package com.alura.forohub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistraRespuesta(
    @NotBlank String mensaje,
    @NotNull Long idTopico,
    @NotNull Long idUsuario,
    String solucion
) {}
