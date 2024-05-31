package com.utp.biblioteca.resources.modelo;

public class Libro {
    private int libro_id;
    private String titulo;
    private String autor;
    private String isbn;
    private String link_imagen;
    private String descripcion;
    private int estok;
    private boolean disponible;


    public int getLibro_id() {
        return libro_id;
    }

    public void setLibro_id(int libro_id) {
        this.libro_id = libro_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLink_imagen() {
        return link_imagen;
    }

    public void setLink_imagen(String link_imagen) {
        this.link_imagen = link_imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstok() {
        return estok;
    }

    public void setEstok(int estok) {
        this.estok = estok;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "libro_id=" + libro_id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                ", link_imagen='" + link_imagen + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estok=" + estok +
                ", disponible=" + disponible +
                '}';
    }
}