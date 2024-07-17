/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Beans/Bean.java to edit this template
 */
package com.utp.biblioteca.resources.controlador.beans;

import com.utp.biblioteca.resources.modelo.Rol;
import com.utp.biblioteca.resources.modelo.Usuario;
import com.utp.biblioteca.resources.modelo.dao.RolDao;
import com.utp.biblioteca.resources.modelo.dao.UsuarioDao;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {

    private UsuarioDao usuarioDao = new UsuarioDao();
    private Usuario usuario = new Usuario();
    private RolDao rolDao = new RolDao();
    private int rolId = 2; // Valor por defecto para usuario

    public UsuarioBean() {
        usuario.setEstado(true);
        usuario.setContraseña(""); // Contraseña vacía por defecto
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
        try {
            Rol rolCompleto = rolDao.buscarUno(rolId);
            usuario.setRol(rolCompleto);
            usuarioDao.crear(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario registrado", "El usuario se registró correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Hubo un problema al registrar el usuario"));
        }
    }
}
