package com.daturism.taller3.Config;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    // Generar un token JWT
    public String generateToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        // Extraer el rol del usuario (solo uno)
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()  // Solo un rol, ya que se usa un único rol por usuario
                .orElse(null);  // Si no hay rol, es null

        // Generar el token JWT
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())  // Nombre de usuario
                .claim("role", role)  // Agregar el rol al token
                .setIssuedAt(new Date())  // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))  // Fecha de expiración
                .signWith(SignatureAlgorithm.HS512, jwtSecret)  // Firmar el token
                .compact();
    }

    // Validar el token JWT
    public boolean validateToken(String token) {
        try {
            // Intentar analizar el token
            Jwts.parser()
                    .setSigningKey(jwtSecret)  // Clave para verificar la firma
                    .parseClaimsJws(token);  // Analizar el token

            return true;  // El token es válido
        } catch (SignatureException e) {
            System.out.println("Firma JWT inválida: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Token JWT malformado: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("Token JWT expirado: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Token JWT no soportado: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT vacío: " + e.getMessage());
        }

        return false;  // Si ocurre algún error, el token no es válido
    }

    // Obtener el nombre de usuario del token
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();  // Obtener los claims del token

        return claims.getSubject();  // Obtener el nombre de usuario
    }

    // Obtener el rol del token (único)
    public String getRoleFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();  // Obtener los claims del token

        return claims.get("role", String.class);  // Obtener el rol del claim
    }

    // Obtener roles o autorizaciones del token
    public List<GrantedAuthority> getAuthorities(String token) {
        String role = getRoleFromToken(token);  // Obtener el rol del token
        return role != null ? List.of(new SimpleGrantedAuthority(role)) : List.of();  // Convertir el rol a una lista de GrantedAuthority
    }
}
