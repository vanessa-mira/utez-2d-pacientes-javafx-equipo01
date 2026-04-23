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

public class MainController {
    @FXML private TableView<Paciente> tblPacientes; // referencia a la tabla
    @FXML private TableColumn<Paciente, String> colCurp; // columna curp
    @FXML private TableColumn<Paciente, String> colNombre; // columna nombre
    @FXML private TableColumn<Paciente, Integer> colEdad; // columna estatus
    @FXML private TableColumn<Paciente, String> colTel;
    @FXML private TableColumn<Paciente, String> colAlergias;
    @FXML private TableColumn<Paciente, String> colEstatus;

    @FXML private Label lblTotal, lblActivos, lblInactivos; // etiquetas de resumen

    private final PacienteService service = new PacienteService(); // crea el servicio de datos

    @FXML
    public void initialize() {
        // configura que columna muestra que dato del objeto paciente
        colCurp.setCellValueFactory(new PropertyValueFactory<>("curp"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colAlergias.setCellValueFactory(new PropertyValueFactory<>("alergias"));
        colEstatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));
        tblPacientes.setItems(service.getLista()); // pone los datos en la tabla
        actualizarInterfaz(); // calcula los numeros de resumen
    }

    public void actualizarInterfaz() {
        if (service.getLista() == null) return; // si no hay lista no hace nada
        long total = service.getLista().size(); // cuenta total de pacientes
        long activos = service.getLista().stream().filter(Paciente::isActivo).count(); // cuenta activos
        long inactivos = total - activos; // calcula inactivos

        lblTotal.setText("Total: " + total); // actualiza texto total
        lblActivos.setText("Activos: " + activos); // actualiza texto activos
        lblInactivos.setText("Inactivos: " + inactivos); // actualiza texto inactivos

        service.guardar(); // guarda todo en el archivo
        tblPacientes.refresh(); // actualiza la tabla visualmente
    }

    @FXML void onOpenForm() { abrirVentana(null); } // abre el formulario vacio

    @FXML void onEdit() {
        Paciente seleccionado = tblPacientes.getSelectionModel().getSelectedItem(); // ve quien esta seleccionado
        if (seleccionado != null) abrirVentana(seleccionado); // si hay alguien abre para editar
        else mostrarAlerta("Atencion", "Selecciona un paciente", Alert.AlertType.WARNING); // avisa si no selecciono nada
    }

    @FXML void onToggleStatus() {
        Paciente seleccionado = tblPacientes.getSelectionModel().getSelectedItem(); // ve quien esta seleccionado
        if (seleccionado != null) {
            service.cambiarEstatus(seleccionado); // cambia su estado
            actualizarInterfaz(); // actualiza la pantalla
        }
    }

    @FXML void onDelete() {
        Paciente seleccionado = tblPacientes.getSelectionModel().getSelectedItem(); // ve quien esta seleccionado
        if (seleccionado != null) {
            service.eliminar(seleccionado); // borra al paciente
            actualizarInterfaz(); // actualiza la pantalla
        }
    }

    private void abrirVentana(Paciente p) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/consultorio/views/form-view.fxml"));
            Parent root = loader.load(); // carga el diseño del formulario

            Stage stage = new Stage(); // crea la ventana nueva
            stage.setTitle(p == null ? "Nuevo Paciente" : "Editar Paciente"); // pone el titulo
            stage.initModality(Modality.APPLICATION_MODAL); // bloquea la ventana principal
            stage.setScene(new Scene(root)); // pone el diseño en la ventana

            FormController controller = loader.getController(); // obtiene el controlador del formulario
            controller.preparar(service.getLista(), p, this); // le manda los datos para trabajar

            stage.showAndWait(); // muestra la ventana y espera a que se cierre
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo abrir el formulario", Alert.AlertType.ERROR); // avisa si falla
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo); // crea la alerta
        alert.setTitle(titulo); // pone el titulo
        alert.setHeaderText(null); // quita el encabezado
        alert.setContentText(mensaje); // pone el mensaje
        alert.showAndWait(); // la muestra
    }
}