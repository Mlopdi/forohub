package com.alura.forohub.dto;

import java.util.List;

public record DatosSalidaUsuario(Long id, String nombre, List<DatosSalidaPerfil> perfiles) {}
