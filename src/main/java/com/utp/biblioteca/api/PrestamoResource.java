/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.utp.biblioteca.api;

import com.utp.biblioteca.resources.modelo.request.PrestamoRequest;
import com.utp.biblioteca.resources.modelo.dao.PrestamoDao;
import com.utp.biblioteca.resources.modelo.dao.sp.StoredProcedureRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Leo
 */
@Path("prestamo")
@RequestScoped
public class PrestamoResource {

    private StoredProcedureRepository sp = new StoredProcedureRepository();
    private PrestamoDao prestamoDao = new PrestamoDao();

    @POST
    @Path("realizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response realizarPrestamo(PrestamoRequest prestamoRequest) {
        String resultado = sp.spRealizarPrestamo(prestamoRequest.getUsuarioDni(), prestamoRequest.getLibroId(), prestamoRequest.getDias());
        return Response.ok(resultado).build();
    }

    @POST
    @Path("devolver")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response devolverLibro(@QueryParam("prestamoId") int prestamoId) {
        sp.spDevolverLibro(prestamoId);
        return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPrestamos() {
        return Response.ok(prestamoDao.buscarTodos()).build();
    }

    @GET
    @Path("historial/{dni}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response historialUsuarioDni(@PathParam("dni") int dni) {
        return Response.ok(prestamoDao.historialUsuarioDni(dni)).build();
    }
}
