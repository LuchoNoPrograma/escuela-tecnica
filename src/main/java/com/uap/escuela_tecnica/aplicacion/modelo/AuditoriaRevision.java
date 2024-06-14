package com.uap.escuela_tecnica.aplicacion.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class AuditoriaRevision {
    @CreatedBy
    @Column(name = "_registrado_por", updatable = false)
    private Long registradoPorIdUsuario;

    @LastModifiedBy
    @Column(name = "_modificado_por")
    private Long modificadoPorIdUsuario;

    @CreatedDate
    @Column(name = "_fecha_registrado", updatable = false)
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Column(name = "_fecha_modificado")
    private LocalDateTime fechaModificacion;
}