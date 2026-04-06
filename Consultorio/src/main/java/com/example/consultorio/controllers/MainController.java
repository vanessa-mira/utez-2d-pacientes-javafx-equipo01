package com.example.consultorio.controllers;

import com.example.consultorio.models.Paciente;
import com.example.consultorio.services.PacienteService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    @FXML private TableView<Paciente> tblPacientes;
    @FXML private TableColumn<Paciente, String> colCurp;
    @FXML private TableColumn<Paciente, String> colNombre;
    @FXML private TableColumn<Paciente, String> colEstatus;

    @FXML private Label lblTotal, lblActivos, lblInactivos, lblMsg;

    private PacienteService service = new PacienteService();
    private final ObservableList<Paciente> data = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configuración de columnas para TableView
        colCurp.setCellValueFactory(new PropertyValueFactory<>("curp"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEstatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));

        loadData();
    }

    private void loadData() {
        try {
            data.setAll(service.loadData());
            tblPacientes.setItems(data);
            updateSummary();
        } catch (IOException e) {
            lblMsg.setText("Error al cargar el archivo");
        }
    }

    @FXML
    public void onOpenForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/consultorio/views/form-view.fxml"));
            Parent root = loader.load();

            // Enlace con el segundo controlador
            FormController controller = loader.getController();
            controller.setService(this.service);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Nuevo Registro");
            stage.setScene(new Scene(root));
            stage.showAndWait(); // Pausa aquí hasta que se cierre el form

            loadData(); // Refresca al volver
        } catch (IOException e) {
            lblMsg.setText("Error al abrir formulario");
            e.printStackTrace();
        }
    }

    @FXML
    public void onToggleStatus() {
        Paciente seleccionado = tblPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                service.toggleEstatus(seleccionado);
                loadData();
                lblMsg.setText("Estatus actualizado correctamente");
            } catch (IOException e) {
                lblMsg.setText("Error al guardar cambio");
            }
        } else {
            lblMsg.setText("Seleccione un paciente de la tabla");
        }
    }

    private void updateSummary() {
        int total = data.size();
        long activos = data.stream().filter(p -> p.getEstatus().equals("ACTIVO")).count();
        lblTotal.setText("Total: " + total);
        lblActivos.setText("Activos: " + activos);
        lblInactivos.setText("Inactivos: " + (total - activos));
    }

    public void onShowDetails(ActionEvent actionEvent) {
    }
}

