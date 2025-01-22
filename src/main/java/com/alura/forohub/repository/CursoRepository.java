package com.alura.forohub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.forohub.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {}
