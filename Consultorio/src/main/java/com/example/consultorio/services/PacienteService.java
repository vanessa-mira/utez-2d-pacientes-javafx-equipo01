package com.example.consultorio.services;

import com.example.consultorio.models.Paciente;
import com.example.consultorio.repositories.PacienteRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PacienteService {
    private PacienteRepository repo = new PacienteRepository();

    // Lee el archivo y lo convierte en una lista de objetos
    public List<Paciente> loadData() throws IOException {
        List<String> lineas = repo.readAllLines();
        List<Paciente> listaPacientes = new ArrayList<>();

        for (String linea : lineas) {
            if (linea.isBlank()) continue; // Salta las lineas que esten vacias

            String[] datos = linea.split(","); // Separa por comas

            //Se crea al paciente con los datos de la linea
            Paciente p = new Paciente(
                    datos[0], // curp
                    datos[1], // nombre
                    Integer.parseInt(datos[2]), // edad
                    datos[3], // telefono
                    datos[4], // alergias
                    datos[5]  // estatus
            );
            listaPacientes.add(p);
        }
        return listaPacientes;
    }

    //  Se revisa que todo este bien y guarda
    public void addPaciente(String curp, String nombre, int edad, String tel, String alergias) throws IOException {

        // Reglas necesarias (ADVERTENCIAS)
        if (nombre.length() < 5) throw new IllegalArgumentException("Nombre muy corto");
        if (edad < 0 || edad > 120) throw new IllegalArgumentException("Edad no permitida");
        if (tel.length() != 10) throw new IllegalArgumentException("El telefono debe ser de 10 numeros");

        // Se buscan duplicados

        List<Paciente> actuales = loadData();
        for (Paciente p : actuales) {
            if (p.getCurp().equalsIgnoreCase(curp)) {
                throw new IllegalArgumentException("Ese CURP ya existe");
            }
        }

        // Esta funcio guarda
        Paciente nuevo = new Paciente(curp.toUpperCase(), nombre, edad, tel, alergias, "ACTIVO");
        repo.appendNewLine(nuevo.toCSV());
    }

    // Cambia el estatus del paciente si esta  Activa o Inactiva
    public void toggleEstatus(Paciente seleccionado) throws IOException {
        List<Paciente> listaCompleta = loadData();
        List<String> lineasNuevas = new ArrayList<>();

        for (Paciente p : listaCompleta) {
            // Si es el paciente que elegimos,se cambia su estatus
            if (p.getCurp().equalsIgnoreCase(seleccionado.getCurp())) {
                if (p.getEstatus().equals("ACTIVO")) {
                    p.setEstatus("INACTIVO");
                } else {
                    p.setEstatus("ACTIVO");
                }
            }
            // Se agrega la informacion modificada o no, a la lista
            lineasNuevas.add(p.toCSV());
        }

        // Sobreescribimos el archivo con los cambios
        repo.writeAllLines(lineasNuevas);
    }
}