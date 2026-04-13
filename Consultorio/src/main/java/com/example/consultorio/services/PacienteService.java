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
    // Actualizar un paciente existente (usando CURP como ID)
    public void actualizar(Paciente pEditado) {
        for (int i = 0; i < listaPacientes.size(); i++) {
            if (listaPacientes.get(i).getCurp().equals(pEditado.getCurp())) {
                listaPacientes.set(i, pEditado);
                break;
            }
        }
        guardar();
    }

    public void eliminar(Paciente p) {
        listaPacientes.remove(p);
        guardar();
    }

    public void cambiarEstatus(Paciente p) {
        if (p != null) {
            p.setActivo(!p.isActivo());
            guardar();
        }
    }

    public boolean existeCurp(String curp) {
        return listaPacientes.stream().anyMatch(p -> p.getCurp().equalsIgnoreCase(curp));
    }
}

