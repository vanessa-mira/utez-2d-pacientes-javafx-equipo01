package com.example.consultorio.models;

public class Paciente {
    private String curp;
    private String nombre;
    private int edad;
    private String tel;
    private String alergias;
    private String estatus; // ACTIVO o INACTIVO

    public Paciente(String curp, String nombre, int edad, String tel, String alergias, String estatus) {
        this.curp = curp;
        this.nombre = nombre;
        this.edad = edad;
        this.tel = tel;
        this.alergias = alergias;
        this.estatus = estatus;
    }

    // Getters y Setters (Necesarios para el TableView)
    public String getCurp() {
        return curp;
    }
    public void setCurp(String curp) {
        this.curp = curp; }
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
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getAlergias() {
        return alergias;
    }
    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }
    public String getEstatus() {
        return estatus;
    }
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public String toString() {

    }

    public String toCSV() {

    }
}
