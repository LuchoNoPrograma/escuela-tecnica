package com.uap.escuela_tecnica.autenticacion.modelo.entidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(name = "nombre_usuario", nullable = false, length = 50)
    private String nombreUsuario;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "habil", columnDefinition = "boolean default true")
    private boolean habil;

    /*
    * Relacion muchos a muchos en la que se representa mediante una tabla intermedia
    * Tiene una validacion de que las combinaciones de columnas id_rol e id_usuario son unicas
    *
    * */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol",
            joinColumns = @JoinColumn(name = "id_usuario"),
            foreignKey = @ForeignKey(name = "fk_usuario_tiene_rol"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"),
            inverseForeignKey = @ForeignKey(name = "fk_rol_se_asigna_a_un_usuario"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_rol"})
    )
    private List<Rol> listaRol;
}
