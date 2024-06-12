package com.uap.escuela_tecnica.gestioncursos.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uap.escuela_tecnica.aplicacion.anotaciones.GenerarTypescript;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO for {@link com.uap.escuela_tecnica.gestioncursos.modelo.entidad.CursoEjecucion}
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@GenerarTypescript
public class CursoEjecucionDto {
    private Long idCursoEjecucion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalDate fechaLimite;
    private String certificadoImg;
    private Boolean enEjecucion;
}