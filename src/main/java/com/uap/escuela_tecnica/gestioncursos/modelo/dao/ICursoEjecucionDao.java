package com.uap.escuela_tecnica.gestioncursos.modelo.dao;

import com.uap.escuela_tecnica.gestioncursos.modelo.entidad.CursoEjecucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICursoEjecucionDao extends JpaRepository<CursoEjecucion, Long> {

    /*
     * Solo devolvera una instancia de un curso en ejecucion por cada curso,
     * la columna en ejecucion es importante para evitar duplicidad de cursos
     * */
    @Query("""
            SELECT ce FROM CursoEjecucion ce
            LEFT JOIN ce.curso ON ce.curso.idCurso = ce.curso.idCurso
             WHERE ce.enEjecucion = true
            """)
    List<CursoEjecucion> listarCursosEnEjecucionConCurso();
}