package com.example.consultorio.controllers; // CAMBIADO A CONSULTORIO

import com.example.consultorio.models.Paciente;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class FormController {
    @FXML private TextField txtCurp, txtNombre, txtEdad, txtTelefono, txtAlergias;
    @FXML private Label lblError;

    private ObservableList<Paciente> listaRef;
    private Paciente pEditar;
    private MainController main;

    public void preparar(ObservableList<Paciente> lista, Paciente p, MainController m) {
        this.listaRef = lista;
        this.pEditar = p;
        this.main = m;

        if (p != null) {
            txtCurp.setText(p.getCurp());
            txtNombre.setText(p.getNombre());
            txtEdad.setText(String.valueOf(p.getEdad()));
            txtTelefono.setText(p.getTelefono());
            txtAlergias.setText(p.getAlergias());
            txtCurp.setDisable(true);
        }
    }

    @FXML
    void onSave() {
        if (lblError != null) lblError.setText("");

        String curp = txtCurp.getText().trim();
        String nombre = txtNombre.getText().trim();
        String tel = txtTelefono.getText().trim();
        String edadRaw = txtEdad.getText().trim();
        String alergias = txtAlergias.getText().trim();

        try {
            if (curp.isEmpty() || nombre.isEmpty() || tel.isEmpty() || edadRaw.isEmpty()) {
                if (lblError != null) lblError.setText("Todos los campos son obligatorios");
                return;
            }

            int edad = Integer.parseInt(edadRaw);

            if (pEditar == null) {
                listaRef.add(new Paciente(curp, nombre, edad, tel, alergias, "Activo"));
            } else {
                pEditar.setNombre(nombre);
                pEditar.setEdad(edad);
                pEditar.setTelefono(tel);
                pEditar.setAlergias(alergias);
            }

            main.actualizarInterfaz();
            cerrar();

        } catch (NumberFormatException e) {
            if (lblError != null) lblError.setText("La edad debe ser un número");
        }
    }

    @FXML void onCancel() { cerrar(); }

    private void cerrar() {
        ((Stage) txtCurp.getScene().getWindow()).close();
    }
}

