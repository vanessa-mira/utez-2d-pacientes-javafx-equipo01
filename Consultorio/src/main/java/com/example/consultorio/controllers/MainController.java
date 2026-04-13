package com.example.consultorio.controllers; // CAMBIADO A CONSULTORIO

import com.example.consultorio.models.Paciente;
import com.example.consultorio.services.PacienteService;
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
    @FXML
    private TableView<Paciente> tblPacientes;
    @FXML
    private TableColumn<Paciente, String> colCurp;
    @FXML
    private TableColumn<Paciente, String> colNombre;
    @FXML
    private TableColumn<Paciente, String> colEstatus;
    @FXML
    private Label lblTotal, lblActivos, lblInactivos;

    private final PacienteService service = new PacienteService();

    @FXML
    public void initialize() {
        colCurp.setCellValueFactory(new PropertyValueFactory<>("curp"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEstatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));
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

    @FXML
    void onOpenForm() {
        abrirVentana(null);
    }

    @FXML
    void onEdit() {
        Paciente seleccionado = tblPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) abrirVentana(seleccionado);
        else mostrarAlerta("Atención", "Selecciona un paciente", Alert.AlertType.WARNING);
    }

    @FXML
    void onToggleStatus() {
        Paciente seleccionado = tblPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            service.cambiarEstatus(seleccionado);
            actualizarInterfaz();
        }
    }

    @FXML
    void onDelete() {
        Paciente seleccionado = tblPacientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            service.eliminar(seleccionado);
            actualizarInterfaz();
        }
    }

    private void abrirVentana(Paciente p) {
        try {
            // RUTA ACTUALIZADA A CONSULTORIO
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/consultorio/views/form-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(p == null ? "Nuevo Paciente" : "Editar Paciente");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            FormController controller = loader.getController();
            controller.preparar(service.getLista(), p, this);

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo abrir el formulario.", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
