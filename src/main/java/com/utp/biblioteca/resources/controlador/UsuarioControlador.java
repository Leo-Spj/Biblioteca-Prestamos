package com.utp.biblioteca.resources.controlador;

import com.utp.biblioteca.resources.dao.UsuarioDAO;
import com.utp.biblioteca.resources.modelo.Usuario;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/usuarios")
public class UsuarioControlador {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuario(@PathParam("id") int id) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.buscarUno(id);
    }
}
