package com.alura.forohub.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.forohub.dto.DatosRegistraUsuario;
import com.alura.forohub.dto.DatosSalidaPerfil;
import com.alura.forohub.dto.DatosSalidaUsuario;
import com.alura.forohub.model.Perfil;
import com.alura.forohub.model.Usuario;
import com.alura.forohub.repository.PerfilRepository;
import com.alura.forohub.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    private UsuarioRepository usuarioRepository;
    private PerfilRepository perfilRepository;

    public UsuarioController(
        UsuarioRepository usuarioRepository,
        PerfilRepository perfilRepository
    ) {
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
    }

    @GetMapping
    public ResponseEntity<List<DatosSalidaUsuario>> obtenerUsuarios(){
        return  ResponseEntity.ok(usuarioRepository.findAll().stream()
                .map(u -> new DatosSalidaUsuario(
                        u.getId(),
                        u.getNombre(),
                        u.getPerfiles()
                                .stream().map(p -> new DatosSalidaPerfil(
                                        p.getId(),
                                        p.getNombre())).toList())).collect(Collectors.toList()));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> guardaUsuario(@RequestBody @Valid DatosRegistraUsuario datosRegistraUsuario,
                                           UriComponentsBuilder uriComponentsBuilder) {
        Optional<Perfil> perfilOpcional = perfilRepository.findById(1L);

        if (perfilOpcional.isPresent()) {
            Usuario usuario = new Usuario(datosRegistraUsuario);

            List<Perfil> perfiles = new ArrayList<>();
            perfiles.add(perfilOpcional.get());
            usuario.setPerfiles(perfiles);
            usuarioRepository.save(usuario);
            URI uri = uriComponentsBuilder.path("/perfiles/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(uri).body(new DatosSalidaUsuario(usuario.getId(), usuario.getNombre(), (List<DatosSalidaPerfil>) usuario.getPerfiles().stream().map(p -> new DatosSalidaPerfil(p.getId(), p.getNombre())).toList()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfil no encontrado");
        }
    }
}
