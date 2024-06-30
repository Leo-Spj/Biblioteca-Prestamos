/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.utp.biblioteca.resources.controlador.beans;

import com.utp.biblioteca.resources.modelo.Autor;
import com.utp.biblioteca.resources.modelo.Libro;
import com.utp.biblioteca.resources.modelo.dao.AutorDao;
import com.utp.biblioteca.resources.modelo.dao.LibroDao;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author leo
 */
@Named
@ViewScoped
public class LibroBean implements Serializable {

    private LibroDao libroDao = new LibroDao();
    private Libro libro = new Libro();
    private AutorDao autorDao = new AutorDao();
    private int autorId;


    public LibroBean() {
    }

    public List<Autor> getAutores() {
        return autorDao.buscarTodos();
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int autorId) {
        this.autorId = autorId;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }


    public void submit() {
        Autor autorCompleto = autorDao.buscarUno(autorId);

        libro.setAutor(autorCompleto);

        System.out.println("Libro: " + libro);
        libroDao.crear(libro);
    }
}