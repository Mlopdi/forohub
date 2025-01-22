package com.alura.forohub.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alura.forohub.model.Curso;
import com.alura.forohub.repository.CursoRepository;

import jakarta.validation.constraints.NotNull;

@Service
public class CursoService {
    private CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso buscarCurso(@NotNull Long id) {
        Optional<Curso> cursoOpcional = cursoRepository.findById(id);
        if (cursoOpcional.isPresent()){
            return cursoOpcional.get();
        }else {
            throw new RuntimeException("Curso no encontrado.");
        }
    }
}
