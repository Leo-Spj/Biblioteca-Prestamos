package com.utp.biblioteca.resources.dao;

import com.utp.biblioteca.resources.configuracion.Conexion;
import com.utp.biblioteca.resources.modelo.Usuario;
import com.utp.biblioteca.resources.repositorio.UsuarioRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements UsuarioRepository {

    Conexion con;
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public void crear(Usuario entidad) {
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("INSERT INTO Usuario (nombre, correo, contraseña, rol_id) VALUES (?, ?, ?, ?)");
            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getCorreo());
            ps.setString(3, entidad.getContraseña());
            ps.setInt(4, entidad.getRol_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Usuario> buscarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("SELECT * FROM Usuario");
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setUsuario_id(rs.getInt("usuario_id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setRol_id(rs.getInt("rol_id"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public Usuario buscarUno(Integer integer) {
        Usuario usuario = new Usuario();
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("SELECT * FROM Usuario WHERE usuario_id = ?");
            ps.setInt(1, integer);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario.setUsuario_id(rs.getInt("usuario_id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setRol_id(rs.getInt("rol_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public void actualizar(Usuario entidad) {
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("UPDATE Usuario SET nombre = ?, correo = ?, contraseña = ?, rol_id = ? WHERE usuario_id = ?");
            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getCorreo());
            ps.setString(3, entidad.getContraseña());
            ps.setInt(4, entidad.getRol_id());
            ps.setInt(5, entidad.getUsuario_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Integer integer) {
        try {
            conn = con.getConectar();
            ps = conn.prepareStatement("DELETE FROM Usuario WHERE usuario_id = ?");
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
            ps = conn.prepareStatement("SELECT * FROM Usuario WHERE usuario_id = ?");
            ps.setInt(1, integer);
            rs = ps.executeQuery();
            existe = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }
}