package org.example.proyecto_conjunto.dao;

import org.example.proyecto_conjunto.model.Tarea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TareaDAO {
    public List<Tarea> getTareasByProject(int proyectoId) {
        List<Tarea> tasks = new ArrayList<>();
        String query = "SELECT * FROM tareas WHERE proyecto_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, proyectoId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tasks.add(new Tarea(
                        rs.getInt("id"),
                        rs.getInt("proyecto_id"),
                        rs.getString("titulo"),
                        rs.getString("estado"),
                        rs.getDate("fecha_limite")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public boolean insertTarea(Tarea tarea) {
        String query = "INSERT INTO tareas (proyecto_id, titulo, estado, fecha_limite) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, tarea.getProyectoId());
            ps.setString(2, tarea.getTitulo());
            ps.setString(3, tarea.getEstado());
            ps.setDate(4, tarea.getFechaLimite());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTareaEstado(int tareaId, String nuevoEstado) {
        String query = "UPDATE tareas SET estado = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, tareaId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
