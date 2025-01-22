package com.alura.forohub.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DatosRegistraUsuario(
    Long id,
    @NotBlank String nombre,
    @NotBlank @Email String correoElectronico,
    @NotBlank @Pattern(regexp = ".{6,}", message = "La contraseña debe tener al menos 6 caracteres.") String contrasena,
    List<DatosRegistraPerfil> perfiles
) {}
