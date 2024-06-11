package com.uap.escuela_tecnica.autenticacion.config;

import com.uap.escuela_tecnica.autenticacion.modelo.entidad.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class UserDetailsImpl implements UserDetails {
    private final Usuario usuario;

    public UserDetailsImpl(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getIdUsuario() {
        return usuario.getIdUsuario();
    }


    /**
     * Tanto ROLE y AUTHORITY pertenecen a la clase SimpleGrantedAuthority
     * <p>
     * ROLE_ es un prefijo para diferenciar de un
     * AUTHORITY
     * <p>
     * Requerido establecerlo para poder otorgar a un usuario las autoridades que permitan acceder a un recurso
     * <p>
     * <p>
     * Utilizar la anotacion: (Para restringir acceso a un recurso con solo anotandolo encima del metodo)
     *
     * @PreAuthorize (metodoEspecial ( " Nombre "))            -> Nombre Original en BD
     * @PostAuthorize (metodoEspecial ( " Nombre "))           -> Nombre Original en BD
     * @PreAuthorize (hasRole ( " NOMBRE_ROLE "))              -> ROLE_NOMBRE_ROLE
     * @PreAuthorize (hasAnyRole ( " NOMBRE_ROLE "))           -> ROLE_NOMBRE_ROLE
     * @PreAuthorize (hasAuthority ( " ROLE_NOMBRE_ROLE "))    -> ROLE_NOMBRE_ROLE
     * @PreAuthorize (hasAnyAuthority ( " ROLE_NOMBRE_ROLE ")) -> ROLE_NOMBRE_ROLE
     * @PreAuthorize (hasAuthority ( " NOMBRE_AUTORIDAD "))    -> NOMBRE_AUTORIDAD
     * @PreAuthorize (hasAnyAuthority ( " NOMBRE_AUTORIDAD ")) -> NOMBRE_AUTORIDAD
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();

        usuario.getListaRol().forEach(rol -> authorities.add(new SimpleGrantedAuthority(rol.getNombre())));

        return authorities;
    }

    @Override
    public String getPassword() {
        return usuario.getContrasena();
    }

    @Override
    public String getUsername() {
        return usuario.getNombreUsuario();
    }

    @Override
    public boolean isEnabled() {
        return usuario.isHabil();
    }
}
