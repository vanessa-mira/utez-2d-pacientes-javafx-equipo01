package com.example.consultorio.services;

import com.example.consultorio.models.Paciente;
import com.example.consultorio.repositories.PacienteRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class PacienteService {
    private PacienteRepository repo = new PacienteRepository();
    private ObservableList<Paciente> listaPacientes = FXCollections.observableArrayList();

    public PacienteService() {
        cargarDatos();
    }

    // Carga el archivo
    private void cargarDatos() {
        List<Paciente> desdeArchivo = repo.leer();
        listaPacientes.setAll(desdeArchivo);
    }

    // Retorna la lista  para la Tabla
    public ObservableList<Paciente> getLista() {
        return listaPacientes;
    }

    // Guarda los cambios en el archivo
    public void guardar() {
        repo.guardar(listaPacientes);
    }

    // Agrega nuevo paciente
    public void agregar(Paciente p) {
        listaPacientes.add(p);
        guardar();
    }

}