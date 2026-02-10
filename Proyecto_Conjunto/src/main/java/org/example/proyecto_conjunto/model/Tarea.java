package org.example.proyecto_conjunto.model;

import java.sql.Date;

public class Tarea {
    private int id;
    private int proyectoId;
    private String titulo;
    private String estado;
    private Date fechaLimite;

    public Tarea(int id, int proyectoId, String titulo, String estado, Date fechaLimite) {
        this.id = id;
        this.proyectoId = proyectoId;
        this.titulo = titulo;
        this.estado = estado;
        this.fechaLimite = fechaLimite;
    }

    public Tarea(int proyectoId, String titulo, String estado, Date fechaLimite) {
        this.proyectoId = proyectoId;
        this.titulo = titulo;
        this.estado = estado;
        this.fechaLimite = fechaLimite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(int proyectoId) {
        this.proyectoId = proyectoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
}
