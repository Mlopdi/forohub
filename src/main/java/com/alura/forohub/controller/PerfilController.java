package com.alura.forohub.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.forohub.dto.DatosRegistraPerfil;
import com.alura.forohub.dto.DatosSalidaPerfil;
import com.alura.forohub.model.Perfil;
import com.alura.forohub.repository.PerfilRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/perfiles")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {
    private PerfilRepository perfilRepository;

    public PerfilController(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    @GetMapping()
    public ResponseEntity<List<DatosSalidaPerfil>> obtenerPerfiles() {
        return ResponseEntity.ok(perfilRepository.findAll().stream()
            .map(DatosSalidaPerfil::new).collect(Collectors.toList()));
    }

    @Transactional
    @PostMapping()
    public ResponseEntity<DatosSalidaPerfil> guardaPerfil(@RequestBody @Valid DatosRegistraPerfil datosRegistraPerfil, UriComponentsBuilder uriComponentsBuilder) {
        Perfil perfil = new Perfil(datosRegistraPerfil);
        System.out.println(perfil.getId());
        perfilRepository.save(perfil);
        URI uri = uriComponentsBuilder.path("/perfiles/{id}").buildAndExpand(perfil.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosSalidaPerfil(perfil.getId(), perfil.getNombre()));
    }
    
}
