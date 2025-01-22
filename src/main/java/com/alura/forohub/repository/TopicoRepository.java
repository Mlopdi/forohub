package com.alura.forohub.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alura.forohub.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    public Optional<Topico> findByTituloAndMensaje(String titulo, String mensaje);
    
    @Query("""
            SELECT t FROM Topico t 
            WHERE 
            t.curso.nombre = :nombreCurso 
            AND YEAR(t.fechaCreacion) = :anio
            """)
    List<Topico> findByCursoNombreAndFechaCreacionYear(
            @Param("nombreCurso") String nombreCurso,
            @Param("anio") int anio);

    @Query("""
            SELECT t FROM Topico t 
            WHERE YEAR(t.fechaCreacion) = :anio
            """)
    List<Topico> findByFechaCreacionYear(@Param("anio") int anio);

    @Query("""
            SELECT t FROM Topico t 
            WHERE t.curso.nombre = :nombreCurso
            """)
    List<Topico> findByCursoNombre(@Param("nombreCurso") String nombreCurso);
}
