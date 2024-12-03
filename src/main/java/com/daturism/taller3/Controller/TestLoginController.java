package com.daturism.taller3.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TestLoginController {

    @PostMapping("/test-login")
    public ResponseEntity<?> testLogin(@RequestBody Map<String, String> credentials) {
        System.out.println("Recibiendo petición de login");
        System.out.println("Credenciales: " + credentials);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Login recibido");
        response.put("status", "success");

        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", "http://localhost:3000")
                .body(response);
    }
}