package com.example.consultorio.models;

public class Paciente {
    // variables para guardar los datos del paciente
    private String curp;
    private String nombre;
    private int edad;
    private String telefono;
    private String alergias;
    private boolean activo;

    // constructor para crear un paciente nuevo con todos sus datos
    public Paciente(String curp, String nombre, int edad, String telefono, String alergias, String estatus) {
        this.curp = curp;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.alergias = alergias;
        this.activo = estatus.equalsIgnoreCase("Activo"); // convierte el texto a valor verdadero o falso
    }

    // metodos para obtener y cambiar la curp
    public String getCurp() {
        return curp;
    }
    public void setCurp(String curp) {
        this.curp = curp;
    }

    // metodos para obtener y cambiar el nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // metodos para obtener y cambiar la edad
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    // metodos para obtener y cambiar el telefono
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // metodos para obtener y cambiar las alergias
    public String getAlergias() {
        return alergias;
    }
    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    // metodos para saber si esta activo o cambiar su estado
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // devuelve el texto Activo o Inactivo segun el valor de la variable activo
    public String getEstatus() {
        return activo ? "Activo" : "Inactivo";
    }
}