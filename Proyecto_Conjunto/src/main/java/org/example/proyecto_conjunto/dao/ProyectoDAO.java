package org.example.proyecto_conjunto.dao;

import org.example.proyecto_conjunto.model.Proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProyectoDAO {
    public List<Proyecto> getProyectosByUser(int usuarioId) {
        List<Proyecto> projects = new ArrayList<>();
        String query = "SELECT * FROM proyectos WHERE usuario_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, usuarioId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                projects.add(new Proyecto(
                        rs.getInt("id"),
                        rs.getInt("usuario_id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public boolean insertProyecto(Proyecto proyecto) {
        String query = "INSERT INTO proyectos (usuario_id, nombre, descripcion) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, proyecto.getUsuarioId());
            ps.setString(2, proyecto.getNombre());
            ps.setString(3, proyecto.getDescripcion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
