package com.example.consultorio.models;

public class Paciente {
    private String curp;
    private String nombre;
    private int edad;
    private String telefono;
    private String alergias;
    private boolean activo;

    public Paciente(String curp, String nombre, int edad, String telefono, String alergias, String estatus) {
        this.curp = curp;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.alergias = alergias;
        this.activo = estatus.equalsIgnoreCase("Activo");
    }

    // Getters y Setters
    public String getCurp() {
        return curp;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getAlergias() {
        return alergias;
    }
    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getEstatus() {

        return activo ? "Activo" : "Inactivo";
    }
}