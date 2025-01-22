package com.alura.forohub.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.forohub.dto.DatosActualizaTopico;
import com.alura.forohub.dto.DatosRegistraTopico;
import com.alura.forohub.dto.DatosSalidaTopico;
import com.alura.forohub.model.Curso;
import com.alura.forohub.model.Topico;
import com.alura.forohub.model.Usuario;
import com.alura.forohub.repository.TopicoRepository;
import com.alura.forohub.service.CursoService;
import com.alura.forohub.service.TopicoService;
import com.alura.forohub.service.UsuarioService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    private TopicoRepository topicoRepository;
    private UsuarioService usuarioService;
    private CursoService cursoService;
    private TopicoService topicoService;

    public TopicoController(
        TopicoRepository topicoRepository,
        UsuarioService usuarioService,
        CursoService cursoService,
        TopicoService topicoService
    ) {
        this.topicoRepository = topicoRepository;
        this.usuarioService = usuarioService;
        this.cursoService = cursoService;
        this.topicoService = topicoService;
    }

    @GetMapping()
    public ResponseEntity<List<DatosSalidaTopico>> obtenerTopicos(
            @RequestParam(required = false) String nombreCurso,
            @RequestParam(required = false) Integer anio
        ) {

        List<Topico> topicos;

        if (nombreCurso != null && anio != null) {
            topicos = topicoRepository.findByCursoNombreAndFechaCreacionYear(nombreCurso, anio);
        } else if (nombreCurso != null) {
            topicos = topicoRepository.findByCursoNombre(nombreCurso);
        } else if (anio != null) {
            topicos = topicoRepository.findByFechaCreacionYear(anio);
        } else {
            topicos = topicoRepository.findAll();
        }

        List<DatosSalidaTopico> datosSalida = topicos.stream().map(t -> new DatosSalidaTopico(
                t.getId(),
                t.getTitulo(),
                t.getMensaje(),
                t.getFechaCreacion(),
                t.getStatus(),
                t.getAutor().getId(),
                t.getCurso().getId()
        )).collect(Collectors.toList());

        return ResponseEntity.ok(datosSalida);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DatosSalidaTopico> registraTopico(
            @RequestBody @Valid DatosRegistraTopico datosRegistraTopico,
            UriComponentsBuilder uriComponentsBuilder){

        topicoService.validarTopico(datosRegistraTopico.titulo(), datosRegistraTopico.mensaje());
        Usuario autor = usuarioService.buscarAutor(datosRegistraTopico.idAutor());
        Curso curso = cursoService.buscarCurso(datosRegistraTopico.idCurso());
        Topico topico = new Topico();
        topico.agregarFechaStatusCursoAutor(curso, autor, datosRegistraTopico.mensaje(), datosRegistraTopico.titulo());
        topicoRepository.save(topico);
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosSalidaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getId(),
                topico.getCurso().getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosSalidaTopico> obtenTopico(@PathVariable Long id){
        Optional<Topico> topicoOpcional = topicoRepository.findById(id);
        if (topicoOpcional.isPresent()) {
            Topico topico = topicoOpcional.get();
            return ResponseEntity.ok(new DatosSalidaTopico(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getFechaCreacion(),
                    topico.getStatus(),
                    topico.getAutor().getId(),
                    topico.getCurso().getId()));

        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosSalidaTopico> actualizaTopico(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizaTopico datosActualizaTopico,
            UriComponentsBuilder uriComponentsBuilder){
        topicoService.validarTopico(datosActualizaTopico.titulo(), datosActualizaTopico.mensaje());

        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        Topico topico = new Topico();
        if (topicoOptional.isPresent()){
            topico = topicoOptional.get();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

        if (datosActualizaTopico.mensaje()==null && datosActualizaTopico.titulo()==null ){
            return ResponseEntity.status(400).body(null);
        } else if (datosActualizaTopico.mensaje()==null) {
            topico.setTitulo(datosActualizaTopico.titulo());
        } else if (datosActualizaTopico.titulo()==null) {
            topico.setMensaje(datosActualizaTopico.mensaje());
        }else{
            topico.setMensaje(datosActualizaTopico.mensaje());
            topico.setTitulo(datosActualizaTopico.titulo());
        }

        //Actualizamos el topico
        topicoRepository.save(topico);
        //Creamos url
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosSalidaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getId(),
                topico.getCurso().getId()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminaTopico(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

    }
}
