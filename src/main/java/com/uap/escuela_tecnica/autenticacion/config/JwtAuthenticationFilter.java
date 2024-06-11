package com.uap.escuela_tecnica.autenticacion.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        //Si el header Authorization no existe o no tiene el prefijo Bearer
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            //Obtenemos el token
            final String jwt = authHeader.substring(7);
            final String username = jwtService.extraerUsername(jwt);
            /*System.out.println(jwt);
            System.out.println(username);*/

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // Validamos los valores extraidos del token y el contexto de seguridad
            if (username != null && authentication == null) {
                System.out.println("Si llegaste aqui es que tienes username y no hay autenticacion");
                UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);

                // Validamos el token si aun esta vigente y si concuerda con el usuario de la BD
                if (jwtService.validarToken(jwt, userDetails)) {
                    System.out.println("El token es valido y concuerda con el usuario de la BD");
                    // Generamos los detalles de la autenticacion por token
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    //Y establecemos la autenticacion en el contexto de Spring Security
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }
}
