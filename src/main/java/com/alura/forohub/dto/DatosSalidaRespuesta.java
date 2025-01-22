package com.alura.forohub.dto;

import java.time.LocalDateTime;

public record DatosSalidaRespuesta(
    Long id,
    String mensaje,
    LocalDateTime fechaCreacion,
    Long idTopico,
    Long idUsuario,
    String solucion
) {

}
