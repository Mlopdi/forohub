package com.alura.forohub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.forohub.dto.DatosAutenticaUsuario;
import com.alura.forohub.dto.DatosJwtToken;
import com.alura.forohub.model.Usuario;
import com.alura.forohub.service.TokenServicio;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    private AuthenticationManager authenticationManager;
    private TokenServicio tokenServicio;

    public AutenticacionController(AuthenticationManager authenticationManager, TokenServicio tokenServicio) {
        this.authenticationManager = authenticationManager;
        this.tokenServicio = tokenServicio;
    }

    @PostMapping
    public ResponseEntity<DatosJwtToken> autentica(@RequestBody @Valid DatosAutenticaUsuario datosAutenticaUsuario){
        Authentication authToken = new UsernamePasswordAuthenticationToken(
            datosAutenticaUsuario.nombre(),
            datosAutenticaUsuario.contrasena()
        );
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        String jwtToken = tokenServicio.generaToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJwtToken(jwtToken));
    }
}
