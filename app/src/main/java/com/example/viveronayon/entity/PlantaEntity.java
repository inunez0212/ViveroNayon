package com.example.viveronayon.entity;

public class PlantaEntity {

    private String idPlanta;
    private String nombre;
    private String nombreCientifico;
    private String descripcion;
    private String cuidados;
    private String imagen;

    public PlantaEntity() {
    }

    public PlantaEntity(String idPlanta, String nombre, String nombreCientifico, String descripcion, String cuidados, String imagen) {
        this.idPlanta = idPlanta;
        this.nombre = nombre;
        this.nombreCientifico = nombreCientifico;
        this.descripcion = descripcion;
        this.cuidados = cuidados;
        this.imagen = imagen;
    }

    public String getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(String idPlanta) {
        this.idPlanta = idPlanta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCuidados() {
        return cuidados;
    }

    public void setCuidados(String cuidados) {
        this.cuidados = cuidados;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
