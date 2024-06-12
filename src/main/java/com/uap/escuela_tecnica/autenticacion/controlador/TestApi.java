package com.uap.escuela_tecnica.autenticacion.controlador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uap.escuela_tecnica.autenticacion.config.JwtService;
import com.uap.escuela_tecnica.autenticacion.dto.UsuarioDto;
import com.uap.escuela_tecnica.autenticacion.dto.UsuarioIniciarSesionDto;
import com.uap.escuela_tecnica.autenticacion.modelo.dao.IUsuarioDao;
import com.uap.escuela_tecnica.autenticacion.modelo.entidad.Usuario;
import com.uap.escuela_tecnica.autenticacion.modelo.funcionalidad.AutenticacionServicio;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestApi {
    private final AutenticacionServicio autenticacionServicio;
    private final AuthenticationManager authenticationManager;
    private final IUsuarioDao usuarioDao;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    @GetMapping("/public/hola-mundo")
    public ResponseEntity<String> holaMundo() {
        return ResponseEntity.status(200).body("Hola mundo! Esto deberia ser público...");
    }

    @PreAuthorize("hasAnyAuthority('')")
    @GetMapping("/auth/hola-mundo")
    public ResponseEntity<String> holaMundoAutenticado() {
        return ResponseEntity.status(200).body("Hola mundo! Esto solo lo verias si estas autenticado...");
    }

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioDto> registrarNuevoUsuario(@RequestBody UsuarioDto dto) {
        Usuario entidad = modelMapper.map(dto, Usuario.class);

        UsuarioDto usuarioDtoRespuesta = modelMapper.map(autenticacionServicio.registrarNuevoUsuario(entidad), UsuarioDto.class);
        return ResponseEntity.status(200)
                .body(usuarioDtoRespuesta);
    }

    @GetMapping("/usuario/{nombreUsuario}")
    public ResponseEntity<UsuarioDto> perfil(@PathVariable String nombreUsuario) {
        Usuario entidad = usuarioDao.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con el nombre de usuario " + nombreUsuario + " no existe."));

        UsuarioDto usuarioDtoRespuesta = UsuarioDto.builder()
                .nombreUsuario(entidad.getNombreUsuario())
                .habil(entidad.isHabil())
                .build();
        return ResponseEntity.status(200).body(usuarioDtoRespuesta);
    }

    @PostMapping("/generar-token")
    public ResponseEntity<String> autenticarseYGenerarTokenJWT(@RequestBody UsuarioIniciarSesionDto peticionIniciarSesion) throws JsonProcessingException {
        Authentication authenticaion = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(peticionIniciarSesion.getNombreUsuario(), peticionIniciarSesion.getContrasena())
        );


        UserDetails userDetails = (UserDetails) authenticaion.getPrincipal();
        return ResponseEntity.status(200).body(jwtService.crearToken(userDetails));
    }
}