package com.uap.escuela_tecnica.autenticacion.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {
    @Value("${security.jwt.secreto}")
    private String SECRETO;

    @Value("${security.jwt.validez}")
    private long JWT_TOKEN_VALIDEZ;


    /**
     * Cifra la palabra secreta con el algoritmo que se especifica
     *
     * @return el Key para firmar el token
     */
    private Key getSignKey() {
        return new SecretKeySpec(SECRETO.getBytes(), SignatureAlgorithm.HS512.getJcaName());
    }

    /**
     * Metodo que preparar la estructura del payload poblandolo con el contenido de los claims (sub, issu, exp, etc):
     * <b>builder:</b> Construye la estructura del payload.
     * <b>setSubject:</b> Establece el nombre del usuario al payload.
     * <b>setIssuedAt:</b> Establece el tiempo en que se crea.
     * <b>setExpiration:</b> Tiempo que va a durar el token.
     * <b>signWith:</b> Cifra la palabra secreta con el algoritmo que se especifica
     *
     * @param payload Sera el payload que sera usado para construir los claims sobre el.
     * @param subject Establece el username del token
     * @return el String del payload del token
     */
    public String prepararEstructuraToken(Map<String, Object> payload, String subject) {
        return Jwts.builder()
                .setClaims(payload)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDEZ))
                .signWith(this.getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * Es el encargado de crear el token por medio del <code>UserDetails</code> dado como parametro
     * invoca al metodo que construye la estructura (payload)-
     *
     * @param userDetails para obtener el username del usuario autenticado.
     * @return el token [header.payload.signature]
     */
    public String crearToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return prepararEstructuraToken(claims, userDetails.getUsername());
    }

    /**
     * Este metodo solo extrae una parte del token [header.payload.signature] siendo los claims registrados
     * los claims (iss, sub, aude, exp)
     * @param token requiere el token para extraer la parte del claim
     * @return El conjunto de Claims
     */
    public Claims extraerContenidoClaims(String token){
        // parser: convierte a String, establece la clave para determinar si el JWT es valido dentro del header
        return Jwts
                .parserBuilder()
                .setSigningKey(this.getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    /**
     * Extrae del claim el contenido de "exp" para identificar el tiempo de vencimiento del token
     * @param token token a extraer el tiempo de vencimiento
     * @return La fecha de vencimiento.
     */
    public Date extraerTiempoVencimiento(String token){
        return extraerContenidoClaims(token).getExpiration();
    }

    /**
     * Determina si se acepta procesar o no el token de acuerdo a la fecha y hora actual
     * @param token Del que se extrae la fecha de vencimiento
     * @return si ya vencio o no el token
     */
    public boolean isTokenExpiration(String token){
        return this.extraerTiempoVencimiento(token).before(new Date());
    }


    /**
     * Extrae el username del token
     * @param token requiere el token para extraer el username
     * @return El username del token
     */
    public String extraerUsername(String token){
        return extraerContenidoClaims(token).getSubject();
    }
    public boolean validarToken(String token, UserDetails userDetails){
        final String username = this.extraerUsername(token);
        return (username.equals(userDetails.getUsername()) && !this.isTokenExpiration(token));
    }
}
