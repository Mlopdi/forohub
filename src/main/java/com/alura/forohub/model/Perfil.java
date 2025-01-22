package com.alura.forohub.model;

import java.util.ArrayList;
import java.util.List;

import com.alura.forohub.dto.DatosRegistraPerfil;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity
@Table
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToMany(mappedBy = "perfiles")
    private List<Usuario> usuarios = new ArrayList<>();

    public Perfil() {}

    public Perfil(@Valid DatosRegistraPerfil datosRegistraPerfil) {
        this.nombre = datosRegistraPerfil.nombre();
    }

    public Perfil(Long id, String nombre, List<Usuario> usuarios) {
        this.id = id;
        this.nombre = nombre;
        this.usuarios = usuarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    

}
