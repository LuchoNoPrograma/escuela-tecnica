package com.uap.escuela_tecnica.gestioncursos.modelo.dao;

import com.uap.escuela_tecnica.gestioncursos.modelo.entidad.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICursoDao extends JpaRepository<Curso, Long> {
    @Query("SELECT c FROM Curso c ORDER BY c.fechaCreacion DESC")
    List<Curso> listarCursosEnGeneralOrdenFechaCreacionDesc();
}