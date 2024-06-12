package com.uap.escuela_tecnica.gestioncursos.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uap.escuela_tecnica.aplicacion.anotaciones.GenerarTypescript;
import com.uap.escuela_tecnica.gestioncursos.modelo.enums.Modalidad;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for {@link com.uap.escuela_tecnica.gestioncursos.modelo.entidad.Curso}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@GenerarTypescript
public class CursoConEjecucionDto {
    private Long idCurso;
    @NotNull
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotNull(message = "La carga horaria es obligatoria")
    @Max(message = "La carga horaria no puede superar las 5000 horas", value = 5000)
    private Integer cargaHoraria;

    private String aficheImg;

    private String fichaTecnicaPdf;

    @NotNull(message = "La modalidad es obligatoria")
    private Modalidad modalidad;

    @NotNull(message = "La categoria es obligatoria")
    private CategoriaDto categoria;

    private String estado;

    @NotNull(message = "La ejecucion es obligatoria")
    private CursoEjecucionDto cursoEjecucion;
}