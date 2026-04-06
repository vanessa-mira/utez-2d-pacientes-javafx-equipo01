package com.example.consultorio.controllers;

import com.example.consultorio.services.PacienteService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class FormController {
    @FXML private TextField txtCurp, txtNombre, txtEdad, txtTel, txtAlergias;
    @FXML private Label lblError;

    private PacienteService service;

    // Método para recibir el servicio desde el MainController
    public void setService(PacienteService s) {
        this.service = s;
    }

    @FXML
    public void onSave() {
        try {
            // Validar que la edad sea un número (como lo hacías en clase)
            int edad = Integer.parseInt(txtEdad.getText().trim());

            // Enviar al servicio (él se encarga de las demás validaciones y el archivo)
            service.addPaciente(
                    txtCurp.getText().trim(),
                    txtNombre.getText().trim(),
                    edad,
                    txtTel.getText().trim(),
                    txtAlergias.getText().trim()
            );

            // Cerrar la ventana si todo sale bien
            Stage stage = (Stage) txtCurp.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            lblError.setText("La edad debe ser un número");
        } catch (IllegalArgumentException e) {
            lblError.setText(e.getMessage()); // Muestra "Nombre muy corto", "CURP duplicado", etc.
        } catch (Exception e) {
            lblError.setText("Error inesperado al guardar");
        }
    }

    @FXML
    public void onCancel() {
        Stage stage = (Stage) txtCurp.getScene().getWindow();
        stage.close();
    }
}


