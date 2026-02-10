package org.example.proyecto_conjunto.model;

import java.util.Date;

public class Tarea {
    private int id_tarea;
    private String nombre_tarea;
    private String descripcion;
    private Date fecha_tarea;
    private String estado;
    private int id_proyecto;

    public Tarea(int id_tarea, String nombre_tarea, String descripcion, String estado, Date fecha_tarea, int id_proyecto) {
        this.id_tarea = id_tarea;
        this.nombre_tarea = nombre_tarea;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fecha_tarea = fecha_tarea;
        this.id_proyecto = id_proyecto;
    }

    public int getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(int id_tarea) {
        this.id_tarea = id_tarea;
    }

    public int getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(int id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha_tarea() {
        return fecha_tarea;
    }

    public void setFecha_tarea(Date fecha_tarea) {
        this.fecha_tarea = fecha_tarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre_tarea() {
        return nombre_tarea;
    }

    public void setNombre_tarea(String nombre_tarea) {
        this.nombre_tarea = nombre_tarea;
    }
}
