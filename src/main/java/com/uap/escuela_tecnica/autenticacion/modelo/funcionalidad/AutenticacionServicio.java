package com.uap.escuela_tecnica.autenticacion.modelo.funcionalidad;

import com.uap.escuela_tecnica.autenticacion.modelo.dao.IUsuarioDao;
import com.uap.escuela_tecnica.autenticacion.modelo.entidad.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutenticacionServicio {
    private final IUsuarioDao usuarioDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Usuario registrarNuevoUsuario(Usuario usuario) {
        usuario.setContrasena(bCryptPasswordEncoder.encode(usuario.getContrasena()));
        return usuarioDao.save(usuario);
    }
}
