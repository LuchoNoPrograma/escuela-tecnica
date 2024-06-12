package com.uap.escuela_tecnica.gestioncursos.modelo.entidad;

import com.uap.escuela_tecnica.aplicacion.modelo.AuditoriaRevision;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "curso_ejecucion")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CursoEjecucion extends AuditoriaRevision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso_ejecucion", nullable = false)
    private Long idCursoEjecucion;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "fecha_limite")
    private LocalDate fechaLimite;

    @Column(name = "certificado_img")
    private String certificadoImg;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_curso", nullable = false, foreignKey = @ForeignKey(name = "fk_curso_ejecucion_es_de_un_curso"))
    private Curso curso;

    @Column(name = "en_ejecucion")
    private Boolean enEjecucion;
}