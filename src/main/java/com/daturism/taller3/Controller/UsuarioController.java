package com.daturism.taller3.Controller;

import com.daturism.taller3.Model.Usuario;
import com.daturism.taller3.Service.IUsuarioService;
import com.daturism.taller3.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService iUsuarioService;


    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        iUsuarioService.saveUsuario(usuario);
        return usuario;
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id) {
        return iUsuarioService.findUsuario(id);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        iUsuarioService.editUsuario(usuario);
        return usuario;
    }

    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        iUsuarioService.deleteUsuario(id);
        return "Usuario eliminado correctamente";
    }

    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return iUsuarioService.getUsuarios();
    }

    @GetMapping("/buscar")
    public List<Usuario> buscarUsuarios(@RequestParam String palabra) {
        return iUsuarioService.findUsuarioByName(palabra);
    }
}

