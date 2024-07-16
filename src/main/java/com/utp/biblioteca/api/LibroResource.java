/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.utp.biblioteca.api;

import com.utp.biblioteca.resources.modelo.Libro;
import com.utp.biblioteca.resources.modelo.dao.LibroDao;
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
@Path("libro")
@RequestScoped
public class LibroResource {

    private LibroDao libroDao = new LibroDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarLibros() {
        return Response.ok(libroDao.buscarTodos()).build();
    }

    //Busquedas:
        @GET
        @Path("titulo/{titulo}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response buscarPorTitulo(@PathParam("titulo") String titulo) {
            return Response.ok(libroDao.buscarPorTitulo(titulo)).build();
        }
        @GET
        @Path("autor/{autor}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response buscarPorAutor(@PathParam("autor") String autor) {
            return Response.ok(libroDao.buscarPorAutor(autor)).build();
        }
        @GET
        @Path("isbn/{isbn}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response buscarPorIsbn(@PathParam("isbn") String isbn) {
            return Response.ok(libroDao.buscarPorIsbn(isbn)).build();
        }
    //BUSQUEDA por CUALQUIER campo (titulo, autor, isbn):
    @GET
    @Path("buscar/{busqueda}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorCualquierCampo(@PathParam("busqueda") String busqueda) {
        try {
            if(busqueda.equals("null") ||
                    busqueda.equals("undefined") ||
                    busqueda.equals("") ||
                    busqueda.equals(" ")
            ){
                return Response.ok(libroDao.buscarTodos()).build();
            }
            return Response.ok(libroDao.buscarPorCualquierCampo(busqueda)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearLibro(Libro libro) {
        try{
            libroDao.crear(libro);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarLibro(@PathParam("id") int id, Libro libro) {
        if(libro.getLibro_id() == id){
            libroDao.actualizar(libro);
            return Response.ok(libro).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarLibro(@PathParam("id") int id) {
        Libro libro = libroDao.buscarUno(id);
        libroDao.eliminar(libro.getLibro_id());
        return Response.status(Response.Status.NO_CONTENT).entity(libro).build();
    }
}
