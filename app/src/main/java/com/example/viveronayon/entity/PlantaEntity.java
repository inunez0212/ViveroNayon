package com.example.viveronayon.entity;

public class PlantaEntity {

    private int idPlanta;
    private String nombre;
    private String nombreCientifico;
    private String descripcion;
    private String consejo;
    private int imagen;

    public PlantaEntity() {
    }

    public PlantaEntity(int idPlanta, String nombre, String nombreCientifico, String descripcion, String consejo, int imagen) {
        this.idPlanta = idPlanta;
        this.nombre = nombre;
        this.nombreCientifico = nombreCientifico;
        this.descripcion = descripcion;
        this.consejo = consejo;
        this.imagen = imagen;
    }

    public int getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(int idPlanta) {
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

    public String getConsejo() {
        return consejo;
    }

    public void setConsejo(String consejo) {
        this.consejo = consejo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "plantaEntity{" +
                "idPlanta=" + idPlanta +
                ", nombre='" + nombre + '\'' +
                ", nombreCientifico='" + nombreCientifico + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", consejo='" + consejo + '\'' +
                ", imagen=" + imagen +
                '}';
    }
}
