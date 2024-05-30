package com.utp.biblioteca.resources.dao;

import com.utp.biblioteca.resources.configuracion.Conexion;
import com.utp.biblioteca.resources.modelo.Prestamo;
import com.utp.biblioteca.resources.repositorio.PrestamoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO implements PrestamoRepository {

    Conexion con;
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public PrestamoDAO() {
        con = new Conexion();
    }

    @Override
    public void crear(Prestamo entidad) {
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("INSERT INTO Prestamo (usuario_id, libro_id, fecha_prestamo, fecha_devolucion, devuelto) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, entidad.getUsuario_id());
            ps.setInt(2, entidad.getLibro_id());
            ps.setDate(3, entidad.getFecha_prestamo());
            ps.setDate(4, entidad.getFecha_devolucion());
            ps.setBoolean(5, entidad.isDevuelto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Prestamo> buscarTodos() {
        List<Prestamo> prestamos = new ArrayList<>();
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("SELECT * FROM Prestamo");
            rs = ps.executeQuery();
            while (rs.next()) {
                Prestamo prestamo = new Prestamo();
                prestamo.setPrestamo_id(rs.getInt("prestamo_id"));
                prestamo.setUsuario_id(rs.getInt("usuario_id"));
                prestamo.setLibro_id(rs.getInt("libro_id"));
                prestamo.setFecha_prestamo(rs.getDate("fecha_prestamo"));
                prestamo.setFecha_devolucion(rs.getDate("fecha_devolucion"));
                prestamo.setDevuelto(rs.getBoolean("devuelto"));
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamos;
    }

    @Override
    public Prestamo buscarUno(Integer integer) {
        Prestamo prestamo = new Prestamo();
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("SELECT * FROM Prestamo WHERE prestamo_id = ?");
            ps.setInt(1, integer);
            rs = ps.executeQuery();
            while (rs.next()) {
                prestamo.setPrestamo_id(rs.getInt("prestamo_id"));
                prestamo.setUsuario_id(rs.getInt("usuario_id"));
                prestamo.setLibro_id(rs.getInt("libro_id"));
                prestamo.setFecha_prestamo(rs.getDate("fecha_prestamo"));
                prestamo.setFecha_devolucion(rs.getDate("fecha_devolucion"));
                prestamo.setDevuelto(rs.getBoolean("devuelto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prestamo;
    }

    @Override
    public void actualizar(Prestamo entidad) {
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("UPDATE Prestamo SET usuario_id = ?, libro_id = ?, fecha_prestamo = ?, fecha_devolucion = ?, devuelto = ? WHERE prestamo_id = ?");
            ps.setInt(1, entidad.getUsuario_id());
            ps.setInt(2, entidad.getLibro_id());
            ps.setDate(3, entidad.getFecha_prestamo());
            ps.setDate(4, entidad.getFecha_devolucion());
            ps.setBoolean(5, entidad.isDevuelto());
            ps.setInt(6, entidad.getPrestamo_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Integer integer) {
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("DELETE FROM Prestamo WHERE prestamo_id = ?");
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
            ps = conn.prepareStatement("SELECT * FROM Prestamo WHERE prestamo_id = ?");
            ps.setInt(1, integer);
            rs = ps.executeQuery();
            existe = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }
}