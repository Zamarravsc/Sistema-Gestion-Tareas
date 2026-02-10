package org.example.proyecto_conjunto.model;

public class Proyecto {
    private int id;
    private int usuarioId;
    private String nombre;
    private String descripcion;

    public Proyecto(int id, int usuarioId, String nombre, String descripcion) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Proyecto(int usuarioId, String nombre, String descripcion) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
