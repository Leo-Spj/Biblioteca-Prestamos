/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.utp.biblioteca.resources.controlador.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.utp.biblioteca.resources.modelo.Libro;
import com.utp.biblioteca.resources.modelo.dao.LibroDao;
import com.utp.biblioteca.resources.modelo.dao.PrestamoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author leo
 */
@WebServlet(urlPatterns = "/libros/")
public class ListadoLibrosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private int obtenerPagina(HttpServletRequest request) {
        String paginaParam = request.getParameter("pagina");
        return (paginaParam != null && !paginaParam.isEmpty()) ? Integer.parseInt(paginaParam) : 1;
    }

    private List<Libro> obtenerLibrosPaginados(int pagina, int cantidad) {
        LibroDao libroDao = new LibroDao();
        return libroDao.buscarPaginado(pagina, cantidad);
    }

    private int obtenerCantidad(HttpServletRequest request) {
        String cantidadParam = request.getParameter("cantidad");
        return (cantidadParam != null && !cantidadParam.isEmpty()) ? Integer.parseInt(cantidadParam) : 21;
    }

    private int obtenerTotalPaginas(int cantidad) {
        LibroDao libroDao = new LibroDao();
        return libroDao.cantidadPaginas(cantidad);
    }

    private void redirigirAPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try {
            int pagina = obtenerPagina(request);
            int cantidad = obtenerCantidad(request);

            // lista de libros paginados
            List<Libro> libros = obtenerLibrosPaginados(pagina, cantidad);
            request.setAttribute("libros", libros);

            // total de páginas
            int totalPaginas = obtenerTotalPaginas(cantidad);
            request.setAttribute("totalPaginas", totalPaginas);

            // atributos adicionales
            request.setAttribute("paginaActual", pagina);
            request.setAttribute("cantidadPorPagina", cantidad);

            // Redirigir a la página JSP
            getServletContext().getRequestDispatcher("/libros/libros.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
