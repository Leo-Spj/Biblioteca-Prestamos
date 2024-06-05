package com.utp.biblioteca.resources.dao;

import java.sql.PreparedStatement;

import com.utp.biblioteca.resources.configuracion.Conexion;
import com.utp.biblioteca.resources.modelo.Autor;
import com.utp.biblioteca.resources.repositorio.AutorRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO implements AutorRepository {

    Conexion con;
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public AutorDAO() {
        con = new Conexion();
    }

    @Override
    public void crear(Autor entidad) {
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("INSERT INTO Autor (nombre) VALUES (?)");
            ps.setString(1, entidad.getNombre());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Autor> buscarTodos() {
        List<Autor> autores = new ArrayList<>();
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("SELECT * FROM Autor");
            rs = ps.executeQuery();
            while (rs.next()) {
                Autor autor = new Autor();
                autor.setAutor_id(rs.getInt("autor_id"));
                autor.setNombre(rs.getString("nombre"));
                autores.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }

    @Override
    public Autor buscarUno(Integer id) {
        Autor autor = new Autor();
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("SELECT * FROM Autor WHERE autor_id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                autor.setAutor_id(rs.getInt("autor_id"));
                autor.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autor;
    }

    @Override
    public void actualizar(Autor entidad) {
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("UPDATE Autor SET nombre = ? WHERE autor_id = ?");
            ps.setString(1, entidad.getNombre());
            ps.setInt(2, entidad.getAutor_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Integer id) {
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("DELETE FROM Autor WHERE autor_id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean existe(Integer id) {
        boolean existe = false;
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("SELECT * FROM Autor WHERE autor_id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            existe = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }
}