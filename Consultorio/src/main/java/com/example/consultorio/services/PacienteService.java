package com.example.consultorio.services;

import com.example.consultorio.models.Paciente;
import com.example.consultorio.repositories.PacienteRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class PacienteService {
    private PacienteRepository repo = new PacienteRepository(); // conecta con el repositorio de archivos
    private ObservableList<Paciente> listaPacientes = FXCollections.observableArrayList(); // lista especial para la tabla

    public PacienteService() {
        cargarDatos(); // carga la informacion del archivo en cuanto se crea el servicio
    }

    private void cargarDatos() {
        List<Paciente> desdeArchivo = repo.leer(); // pide los pacientes al repositorio
        listaPacientes.setAll(desdeArchivo); // mete los pacientes en la lista de la interfaz
    }

    public ObservableList<Paciente> getLista() {
        return listaPacientes; // entrega la lista para que el controlador la use
    }

    public void guardar() {
        repo.guardar(listaPacientes); // manda la lista actual a guardarse en el archivo fisico
    }

    public void eliminar(Paciente p) {
        listaPacientes.remove(p); // borra al paciente de la lista de memoria
        guardar(); // guarda el cambio en el archivo para que desaparezca permanente
    }

    public void cambiarEstatus(Paciente p) {
        if (p != null) {
            p.setActivo(!p.isActivo()); // si era true lo cambia a false y al reves
            guardar(); // guarda el cambio de estado en el archivo
        }
    }
}