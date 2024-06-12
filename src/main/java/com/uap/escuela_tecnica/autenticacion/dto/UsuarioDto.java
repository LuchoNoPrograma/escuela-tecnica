package com.uap.escuela_tecnica.autenticacion.dto;

import com.uap.escuela_tecnica.aplicacion.anotaciones.GenerarTypescript;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.uap.escuela_tecnica.autenticacion.modelo.entidad.Usuario}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@GenerarTypescript
public class UsuarioDto implements Serializable {
    private Long idUsuario;
    private String nombreUsuario;
    private String contrasena;
    private boolean habil;
}