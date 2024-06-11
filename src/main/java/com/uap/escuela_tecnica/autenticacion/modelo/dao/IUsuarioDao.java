package com.uap.escuela_tecnica.autenticacion.modelo.dao;

import com.uap.escuela_tecnica.autenticacion.modelo.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioDao extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}