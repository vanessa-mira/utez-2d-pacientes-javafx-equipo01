package com.example.consultorio.models;

public class Paciente {
    private String curp;
    private String nombre;
    private int edad;
    private String telefono;
    private String alergias;
    private boolean activo;

    public Paciente(String curp, String nombre, int edad, String telefono, String alergias, boolean activo) {
        this.curp = curp;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.alergias = alergias;
        this.activo = activo;
    }

    public String getCurp() { return curp; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getTelefono() { return telefono; }
    public String getAlergias() { return alergias; }
    public boolean isActivo() { return activo; }

    public String getStatusString() {
        return activo ? "ACTIVO" : "INACTIVO";
    }

    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return curp + "," + nombre + "," + edad + "," + telefono + "," + alergias + "," + activo;
    }
}

