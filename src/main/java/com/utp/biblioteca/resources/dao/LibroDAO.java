package com.utp.biblioteca.resources.dao;

import com.utp.biblioteca.resources.configuracion.Conexion;
import com.utp.biblioteca.resources.modelo.Libro;
import com.utp.biblioteca.resources.repositorio.LibroRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO implements LibroRepository {

    Conexion con;
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public LibroDAO() {
        con = new Conexion();
    }

    @Override
    public void crear(Libro entidad) {
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("INSERT INTO Libro (isbn, titulo, autor_id, link_imagen, descripcion, stock) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, entidad.getIsbn());
            ps.setString(2, entidad.getTitulo());
            ps.setInt(3, entidad.getAutor_id());
            ps.setString(4, entidad.getLink_imagen());
            ps.setString(5, entidad.getDescripcion());
            ps.setInt(6, entidad.getStock());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Libro> buscarTodos() {
        List<Libro> libros = new ArrayList<>();
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("SELECT * FROM Libro");
            rs = ps.executeQuery();
            while (rs.next()) {
                Libro libro = new Libro();
                libro.setLibro_id(rs.getInt("libro_id"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor_id(rs.getInt("autor_id"));
                libro.setLink_imagen(rs.getString("link_imagen"));
                libro.setDescripcion(rs.getString("descripcion"));
                libro.setStock(rs.getInt("stock"));
                libros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    @Override
    public Libro buscarUno(Integer integer) {
        Libro libro = new Libro();
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("SELECT * FROM Libro WHERE libro_id = ?");
            ps.setInt(1, integer);
            rs = ps.executeQuery();
            while (rs.next()) {
                libro.setLibro_id(rs.getInt("libro_id"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor_id(rs.getInt("autor_id"));
                libro.setLink_imagen(rs.getString("link_imagen"));
                libro.setDescripcion(rs.getString("descripcion"));
                libro.setStock(rs.getInt("stock"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libro;
    }

    @Override
    public void actualizar(Libro entidad) {
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("UPDATE Libro SET isbn = ?, titulo = ?, autor_id = ?, link_imagen = ?, descripcion = ?, stock = ? WHERE libro_id = ?");
            ps.setString(1, entidad.getIsbn());
            ps.setString(2, entidad.getTitulo());
            ps.setInt(3, entidad.getAutor_id());
            ps.setString(4, entidad.getLink_imagen());
            ps.setString(5, entidad.getDescripcion());
            ps.setInt(6, entidad.getStock());
            ps.setInt(7, entidad.getLibro_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Integer integer) {
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("DELETE FROM Libro WHERE libro_id = ?");
            ps.setInt(1, integer);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean existe(Integer integer) {
        boolean existe = false;
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("SELECT * FROM Libro WHERE libro_id = ?");
            ps.setInt(1, integer);
            rs = ps.executeQuery();
            existe = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }
}
