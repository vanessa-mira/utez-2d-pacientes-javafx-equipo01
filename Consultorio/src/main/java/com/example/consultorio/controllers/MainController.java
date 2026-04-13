package com.example.consultorio.controllers;


import com.example.consultorio.models.Paciente;
import com.example.consultorio.services.PacienteService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private TableView<Paciente> tblPacientes;
    @FXML
    private TableColumn<Paciente, String> colCurp;
    @FXML
    private TableColumn<Paciente, String> colNombre;
    @FXML
    private TableColumn<Paciente, String> colEstatus; // Nota: Paciente debe tener getEstatus() o ser String
    @FXML
    private Label lblTotal, lblActivos, lblInactivos;

    private final PacienteService service = new PacienteService();

    @FXML
    public void initialize() {
        // Configuración de columnas
        colCurp.setCellValueFactory(new PropertyValueFactory<>("curp"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEstatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));

        // Cargar datos
        tblPacientes.setItems(service.getLista());
        actualizarInterfaz();
    }

    public void actualizarInterfaz() {
        if (service.getLista() == null) return;

        long total = service.getLista().size();
        long activos = service.getLista().stream().filter(Paciente::isActivo).count();
        long inactivos = total - activos;

        lblTotal.setText("Total: " + total);
        lblActivos.setText("Activos: " + activos);
        lblInactivos.setText("Inactivos: " + inactivos);

        service.guardar();
        tblPacientes.refresh();
    }
}