package com.uap.escuela_tecnica.autenticacion.config;

import com.uap.escuela_tecnica.autenticacion.modelo.dao.IUsuarioDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IUsuarioDao usuarioDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsImpl(usuarioDao.findByNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con el nombre de usuario " + username + " no existe.")));
    }
}
