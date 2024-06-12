package com.uap.escuela_tecnica.gestioncursos.modelo.entidad;

import com.uap.escuela_tecnica.aplicacion.modelo.AuditoriaRevision;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "descuento")
public class Descuento extends AuditoriaRevision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_descuento", nullable = false)
    private Long idDescuento;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Column(name = "concepto", nullable = false)
    private String concepto;

    @Column(name = "resolucion_pdf")
    private String resolucionPdf;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_curso", nullable = false, foreignKey = @ForeignKey(name = "fk_descuento_pertenece_a_un_curso"))
    private Curso curso;

}