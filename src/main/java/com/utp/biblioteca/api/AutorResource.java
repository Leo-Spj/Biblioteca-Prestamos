/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.utp.biblioteca.api;

import com.utp.biblioteca.resources.modelo.Autor;
import com.utp.biblioteca.resources.modelo.dao.AutorDao;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Leo
 */
@Path("autor")
@RequestScoped
public class AutorResource {

    private AutorDao autorDao = new AutorDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarAutores() {
        return Response.ok(autorDao.buscarTodos()).build();
    }

    @GET
    @Path("{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorNombre(@PathParam("nombre") String nombre) {
        return Response.ok(autorDao.buscarPorNombre(nombre)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearAutor(Autor autor) {
        try {
            autorDao.crear(autor);
            return Response.status(Response.Status.CREATED).entity(autor).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarAutor(@PathParam("id") int id, Autor autor) {
        if(autor.getAutor_id() == id) {
            autorDao.actualizar(autor);
            return Response.ok(autor).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarAutor(@PathParam("id") int id) {
        try {
            autorDao.eliminar(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
