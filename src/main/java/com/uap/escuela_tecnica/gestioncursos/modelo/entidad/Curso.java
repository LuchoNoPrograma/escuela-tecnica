package com.uap.escuela_tecnica.gestioncursos.modelo.entidad;

import com.uap.escuela_tecnica.aplicacion.modelo.AuditoriaRevision;
import com.uap.escuela_tecnica.gestioncursos.modelo.enums.Modalidad;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "curso")
public class Curso extends AuditoriaRevision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso", nullable = false)
    private Long idCurso;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "carga_horaria", nullable = false)
    private Integer cargaHoraria;

    @Column(name = "afiche_img")
    private String aficheImg;

    @Column(name = "ficha_tecnica_pdf")
    private String fichaTecnicaPdf;

    @Enumerated(EnumType.STRING)
    @Column(name = "modalidad", nullable = false)
    private Modalidad modalidad;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_categoria", nullable = false, foreignKey = @ForeignKey(name = "fk_curso_pertenece_a_una_categoria"))
    private Categoria categoria;

    @OneToMany(mappedBy = "curso")
    private List<CursoEjecucion> listaCursoEjecucion;

    @Column(name = "estado", columnDefinition = "varchar(55) default 'ACTIVO'")
    private String estado;
}