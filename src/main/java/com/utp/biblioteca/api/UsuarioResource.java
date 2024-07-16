/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.utp.biblioteca.api;

import com.utp.biblioteca.resources.modelo.Usuario;
import com.utp.biblioteca.resources.modelo.dao.UsuarioDao;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

/**
 * REST Web Service
 *
 * @author Leo
 */
@Path("usuario")
@RequestScoped
public class UsuarioResource {

    UsuarioDao usuarioDao = new UsuarioDao();

    @GET
    @Path("{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerUsuario(@PathParam("dni") int dni) {
        Usuario usuario = usuarioDao.buscarPorDni(dni);
        if(usuario != null) {
            return Response.ok(usuario).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {
        List<Usuario> usuarios = usuarioDao.buscarTodos();
        return Response.ok(usuarios).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearUsuario(Usuario usuario) {
        try{
            usuarioDao.crear(usuario);
            Usuario usuarioCreado = usuarioDao.buscarPorDni(usuario.getDni());
            return Response.status(Response.Status.CREATED).entity(usuarioCreado).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarUsuario(@PathParam("id") int id, Usuario usuario) {
        if(usuario.getUsuario_id() == id){
            usuarioDao.actualizar(usuario);
            return Response.ok(usuario).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarUsuario(@PathParam("dni") int dni) {
        Usuario usuario = usuarioDao.buscarPorDni(dni);
        usuarioDao.eliminar(usuario.getUsuario_id());
        return Response.status(Response.Status.NO_CONTENT).entity(usuario).build();
    }

}
