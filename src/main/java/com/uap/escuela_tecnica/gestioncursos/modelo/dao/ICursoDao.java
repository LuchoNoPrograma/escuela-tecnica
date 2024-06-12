package com.uap.escuela_tecnica.gestioncursos.modelo.dao;

import com.uap.escuela_tecnica.gestioncursos.modelo.entidad.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICursoDao extends JpaRepository<Curso, Long> {

}