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

import com.alura.forohub.dto.DatosRegistraCurso;
import com.alura.forohub.dto.DatosSalidaCurso;
import com.alura.forohub.model.Curso;
import com.alura.forohub.repository.CursoRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {
    private CursoRepository cursoRepository;

    public CursoController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<DatosSalidaCurso> guardaCurso(
        @RequestBody @Valid DatosRegistraCurso datosRegistraCurso,
        UriComponentsBuilder uriComponentsBuilder) {
        Curso curso = cursoRepository.save(new Curso(datosRegistraCurso.id(),
                datosRegistraCurso.nombre(),
                datosRegistraCurso.categoria()));
        URI uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosSalidaCurso(curso.getId(), curso.getNombre(), curso.getCategoria()));
    }

    @GetMapping
    public ResponseEntity<List<DatosSalidaCurso>> obtenerDatos(){
        return ResponseEntity.ok(cursoRepository.findAll().stream().map(c -> new DatosSalidaCurso(
            c.getId()
            , c.getNombre()
            , c.getCategoria())).collect(Collectors.toList()));
    }
}
