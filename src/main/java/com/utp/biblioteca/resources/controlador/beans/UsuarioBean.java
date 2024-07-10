/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Bean.java to edit this template
 */
package com.utp.biblioteca.resources.controlador.beans;

import com.utp.biblioteca.resources.modelo.Rol;
import com.utp.biblioteca.resources.modelo.Usuario;
import com.utp.biblioteca.resources.modelo.dao.RolDao;
import com.utp.biblioteca.resources.modelo.dao.UsuarioDao;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.beans.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author brandonluismenesessolorzano
 */
@Named
@ViewScoped
public class UsuarioBean implements Serializable {

    private UsuarioDao usuarioDao = new UsuarioDao();
    private Usuario usuario = new Usuario();
    private RolDao rolDao = new RolDao();
    private int rolId;

    public UsuarioBean() {
        usuario.setEstado(true);
    }

    public List<Rol> getRoles() {
        return rolDao.buscarTodos();
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void submit() {
        Rol rolCompleto = rolDao.buscarUno(rolId);

        usuario.setRol(rolCompleto);

        System.out.println("Usuario: " + usuario);
        usuarioDao.crear(usuario);
    }
}
