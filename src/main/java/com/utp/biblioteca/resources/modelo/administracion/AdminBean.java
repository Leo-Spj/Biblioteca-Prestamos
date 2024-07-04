package com.utp.biblioteca.resources.modelo.administracion;

import com.utp.biblioteca.resources.modelo.Usuario;
import com.utp.biblioteca.resources.modelo.dao.UsuarioDao;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class AdminBean {
    private List<Usuario> usuarios;
    private UsuarioDao usuarioDao;

    public AdminBean() {
        usuarioDao = new UsuarioDao();
        usuarios = usuarioDao.buscarTodos();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}