/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.utp.biblioteca.resources.controlador.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Leo
 */
@WebServlet(name = "HtmlFileServlet", urlPatterns = {"/gestion/*", "/usuario/*"})
public class HtmlFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String basePath = getServletContext().getRealPath("/");
        String filePath = "";

        if (requestURI.startsWith("/gestion/")) {
            filePath = basePath + "gestion/" + requestURI.substring(requestURI.lastIndexOf('/') + 1) + ".html";
        } else if (requestURI.startsWith("/usuario/")) {
            filePath = basePath + "usuario/" + requestURI.substring(requestURI.lastIndexOf('/') + 1) + ".html";
        }

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            resp.setContentType("text/html");
            resp.getWriter().write(content);
        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
