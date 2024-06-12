package com.uap.escuela_tecnica.gestioncursos.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uap.escuela_tecnica.aplicacion.anotaciones.GenerarTypescript;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for {@link com.uap.escuela_tecnica.gestioncursos.modelo.entidad.Categoria}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@GenerarTypescript
public class CategoriaDto {
    private Long idCategoria;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
}