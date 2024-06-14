package com.uap.escuela_tecnica.gestioncursos.modelo.entidad;

import com.uap.escuela_tecnica.aplicacion.modelo.AuditoriaRevision;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "arancel")
public class Arancel extends AuditoriaRevision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arancel", nullable = false)
    private Long idArancel;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Column(name = "resolucion_pdf")
    private String resolucionPdf;

    @Column(name = "concepto", nullable = false)
    private String concepto;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_curso", nullable = false, foreignKey = @ForeignKey(name = "fk_arancel_pertenece_a_un_curso"))
    private Curso curso;
}