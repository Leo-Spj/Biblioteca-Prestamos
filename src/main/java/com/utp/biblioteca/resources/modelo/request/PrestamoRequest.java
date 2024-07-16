package com.utp.biblioteca.resources.modelo.request;

public class PrestamoRequest {
    private int usuarioDni;
    private int libroId;
    private int dias;

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getLibroId() {
        return libroId;
    }

    public void setLibroId(int libroId) {
        this.libroId = libroId;
    }

    public int getUsuarioDni() {
        return usuarioDni;
    }

    public void setUsuarioDni(int usuarioDni) {
        this.usuarioDni = usuarioDni;
    }
}