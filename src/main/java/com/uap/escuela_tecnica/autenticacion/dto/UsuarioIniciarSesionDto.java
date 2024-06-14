package com.uap.escuela_tecnica.autenticacion.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uap.escuela_tecnica.aplicacion.anotaciones.GenerarTypescript;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.uap.escuela_tecnica.autenticacion.modelo.entidad.Usuario}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@GenerarTypescript
public class UsuarioIniciarSesionDto implements Serializable {
    @NotNull(message = "El nombre de usuario es obligatorio")
    @NotEmpty(message = "El nombre de usuario es obligatorio")
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String nombreUsuario;
    @NotNull(message = "La contraseña es obligatoria")
    @NotEmpty(message = "La contraseña es obligatoria")
    @NotBlank
    private String contrasena;
}