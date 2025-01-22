package com.alura.forohub.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.forohub.dto.DatosRegistraRespuesta;
import com.alura.forohub.dto.DatosSalidaRespuesta;
import com.alura.forohub.model.Respuesta;
import com.alura.forohub.model.Topico;
import com.alura.forohub.model.Usuario;
import com.alura.forohub.repository.RespuestaRepository;
import com.alura.forohub.repository.TopicoRepository;
import com.alura.forohub.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {
    private UsuarioRepository usuarioRepository;
    private TopicoRepository topicoRepository;
    private RespuestaRepository respuestaRepository;

    public RespuestaController(
        UsuarioRepository usuarioRepository,
        TopicoRepository topicoRepository,
        RespuestaRepository respuestaRepository
    ) {
        this.usuarioRepository = usuarioRepository;
        this.topicoRepository = topicoRepository;
        this.respuestaRepository = respuestaRepository;
    }

    @Transactional
    @PostMapping
    ResponseEntity<DatosSalidaRespuesta> guardarRespuesta(
            @RequestBody @Valid DatosRegistraRespuesta datosRegistraRespuesta,
            UriComponentsBuilder uriComponentsBuilder){
        System.out.println("Estos son los datos recibidos " + datosRegistraRespuesta);
        //Buscamos topico y usuario
        Optional<Topico> topicoOpcional = topicoRepository.findById(datosRegistraRespuesta.idTopico());
        Topico topico = new Topico();
        if (topicoOpcional.isPresent()){
            topico = topicoOpcional.get();
        } else {
            throw new RuntimeException("No se consiguió el tópico");
        }

        Optional<Usuario> usuarioOpcional = usuarioRepository.findById(datosRegistraRespuesta.idUsuario());
        Usuario usuario = new Usuario();
        if (usuarioOpcional.isPresent()){
            usuario = usuarioOpcional.get();
        } else {
            throw new RuntimeException("No se consiguió el Autor");
        }

        System.out.println("Topico "+ topico.toString());
        System.out.println(("Usuario " + usuario.toString()));

        Respuesta respuesta = new Respuesta(null,
                datosRegistraRespuesta.mensaje(),
                LocalDateTime.now(),
                topico,
                usuario,
                null);
        respuestaRepository.save(respuesta);
        URI uri = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosSalidaRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getTopico().getId(),
                respuesta.getAutor().getId(),
                respuesta.getSolucion()
        ));
    }
}
