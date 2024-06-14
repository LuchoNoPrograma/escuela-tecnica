package com.uap.escuela_tecnica.autenticacion.controlador;

import com.uap.escuela_tecnica.autenticacion.config.JwtService;
import com.uap.escuela_tecnica.autenticacion.dto.UsuarioIniciarSesionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jwt-auth/")
@Tag(name = "#jwt-api", description = "API para la autenticación de usuarios mediante JWT")
public class JwtAuthenticationApi {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    /*
     * Metodo de seguridad para autenticar un usuario y retornar el JWT generado
     * */
    @Operation(summary = "Autenticar usuario", description = "Metodo de libre acceso para realizar autenticacion y obtener un JWT")
    @PostMapping("/iniciar-sesion")
    public ResponseEntity<String> autenticarYGenerarToken(@RequestBody UsuarioIniciarSesionDto usuarioIniciarSesionDto) {
        //Realizamos una autenticacion por medio de SpringSecurity, que ya se encargara de realizar la autenticacion
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioIniciarSesionDto.getNombreUsuario(), usuarioIniciarSesionDto.getContrasena())
        );

        //Tranformamos de la autenticacion a una clase UserDetails que es el Usuario de Spring Security
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.status(200).body(jwtService.crearToken(userDetails));
    }
}