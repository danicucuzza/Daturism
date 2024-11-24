package com.daturism.taller3.Service;

import com.daturism.taller3.Model.Destino;
import com.daturism.taller3.Model.Usuario;

import java.util.List;

public interface IUsuarioService {

    public List<Usuario> getUsuarios();

    public void saveUsuario(Usuario usuario);

    public Usuario findUsuario(Long id_usuario);

    public void deleteUsuario(Long id_usuario);

    public void editUsuario(Usuario usuario);

    List<Usuario> getUsuariosByIds(List<Long> ids);

    public List<Usuario> findUsuarioByName(String palabra);
}
