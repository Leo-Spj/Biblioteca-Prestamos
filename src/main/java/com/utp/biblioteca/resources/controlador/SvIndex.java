/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.utp.biblioteca.resources.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.utp.biblioteca.resources.dao.LibroDao;
import com.utp.biblioteca.resources.dao.PrestamoDao;
import com.utp.biblioteca.resources.modelo.Libro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "SvIndex", urlPatterns = {"/"})
public class SvIndex extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvIndex</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvIndex at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private int obtenerPagina(HttpServletRequest request) {
        return request.getParameter("pagina") != null ?
                Integer.parseInt(request.getParameter("pagina")) : 1; // Si no se envía el parámetro, se asume la página 1
    }

    private List<Libro> obtenerLibrosPaginados(int pagina, int cantidad) {
        LibroDao libroDao = new LibroDao();
        return libroDao.buscarPaginado(pagina, cantidad);
    }

    private List<Libro> obtenerTopLibros(int cantidad) {
        PrestamoDao prestamoDao = new PrestamoDao();
        return prestamoDao.buscarTop(cantidad);
    }

    private int obtenerCantidad(HttpServletRequest request) {
        return request.getParameter("cantidad") != null ?
                Integer.parseInt(request.getParameter("cantidad")) : 21;
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

            List<Libro> libros = obtenerLibrosPaginados(pagina, cantidad);
            request.setAttribute("libros", libros);

            List<Libro> topLibros = obtenerTopLibros(3);
            request.setAttribute("topLibros", topLibros);

            int totalPaginas = obtenerTotalPaginas(cantidad);
            request.setAttribute("totalPaginas", totalPaginas);

            request.setAttribute("paginaActual", pagina);
            request.setAttribute("cantidadPorPagina", cantidad);


            redirigirAPagina(request, response);

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
