package org.example.proyecto_conjunto.model;

import java.util.Date;

public class Proyecto {
    private int id_proyecto;
    private String nombre_proyecto;
    private String descripcion;
    private Date fecha_proyecto;
    private int id_usuario;

    public Proyecto(int id_proyecto, String nombre_proyecto, String descripcion, Date fecha_proyecto, int id_usuario) {
        this.id_proyecto = id_proyecto;
        this.nombre_proyecto = nombre_proyecto;
        this.descripcion = descripcion;
        this.fecha_proyecto = fecha_proyecto;
        this.id_usuario = id_usuario;
    }

    public int getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(int id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public String getNombre_proyecto() {
        return nombre_proyecto;
    }

    public void setNombre_proyecto(String nombre_proyecto) {
        this.nombre_proyecto = nombre_proyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Date getFecha_proyecto() {
        return fecha_proyecto;
    }

    public void setFecha_proyecto(Date fecha_proyecto) {
        this.fecha_proyecto = fecha_proyecto;
    }
}
