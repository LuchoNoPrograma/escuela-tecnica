package com.uap.escuela_tecnica.gestioncursos.modelo.entidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categoria", uniqueConstraints = {
    @UniqueConstraint(name="uq_categoria_nombre_es_unico", columnNames = "nombre")
})
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria", nullable = false)
    private Long idCategoria;

    @Column(name = "nombre", nullable = false)
    private String nombre;
}