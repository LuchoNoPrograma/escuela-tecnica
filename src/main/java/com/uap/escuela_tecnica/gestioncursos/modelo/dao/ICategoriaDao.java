package com.uap.escuela_tecnica.gestioncursos.modelo.dao;

import com.uap.escuela_tecnica.gestioncursos.modelo.entidad.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaDao extends JpaRepository<Categoria, Long> {
}