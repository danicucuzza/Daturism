package com.daturism.taller3.Controller;

import com.daturism.taller3.Config.JwtTokenProvider;
import com.daturism.taller3.dto.LoginRequest;
import com.daturism.taller3.dto.JwtAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            // Añadir validación explícita de credenciales
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            // Verificar explícitamente el estado de autenticación
            if (!authentication.isAuthenticated()) {
                Map<String, String> errorResponse = new HashMap<>();
                errorResponse.put("mensaje", "Autenticación fallida");
                return ResponseEntity.status(401).body(errorResponse);
            }

            String jwt = tokenProvider.generateToken(authentication);
            Map<String, String> response = new HashMap<>();
            response.put("token", jwt);
            response.put("mensaje", "Inicio de sesión exitoso");
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            // Manejo de errores más detallado
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("mensaje", "Credenciales inválidas: " + e.getMessage());
            return ResponseEntity.status(401).body(errorResponse);
        }
    }
}