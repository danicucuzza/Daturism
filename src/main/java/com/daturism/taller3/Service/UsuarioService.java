package com.daturism.taller3.Service;

import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Model.Usuario;
import com.daturism.taller3.Repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Override
    public List<Usuario> getUsuarios() {
        List<Usuario> listaUsuarios = iUsuarioRepository.findAll();
        return listaUsuarios;
    }

    @Override
    public void saveUsuario(Usuario usuario) {
        iUsuarioRepository.save(usuario);
    }

    @Override
    public Usuario findUsuario(Long id_usuario) {
        Usuario usuario = iUsuarioRepository.findById(id_usuario).orElse(null);
        return usuario;
    }

    @Override
    public void deleteUsuario(Long id_usuario) {
        iUsuarioRepository.deleteById(id_usuario);
    }

    @Override
    public void editUsuario(Usuario usuario) {
        this.saveUsuario(usuario);
    }

    @Override
    public List<Usuario> getUsuariosByIds(List<Long> ids) {
        return iUsuarioRepository.findAllById(ids);
    }

    @Override
    public List<Usuario> findUsuarioByName(String palabra) {
        String palabraLower = palabra.toLowerCase();
        List<Usuario> listaUsuarios = iUsuarioRepository.findAll();
        List<Usuario> listaUsuariosFiltrados = new ArrayList<>();

        for (Usuario usuario : listaUsuarios) {
            String textoComparar = usuario.getNombre();
            if (textoComparar != null && textoComparar.toLowerCase().contains(palabraLower)){
                listaUsuariosFiltrados.add(usuario);
            }
        }
        return listaUsuariosFiltrados;
    }
}
