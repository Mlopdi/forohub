package com.alura.forohub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.alura.forohub.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public UserDetails findByNombre(String username);
}
