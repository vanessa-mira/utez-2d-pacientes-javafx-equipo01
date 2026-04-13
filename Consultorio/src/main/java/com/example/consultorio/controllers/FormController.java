package com.example.consultorio.controllers;

import com.example.consultorio.models.Paciente;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class FormController {
    // campos de texto del formulario
    @FXML private TextField txtCurp, txtNombre, txtEdad, txtTelefono, txtAlergias;
    @FXML private Label lblError; // etiqueta para mostrar errores

    private ObservableList<Paciente> listaRef; // referencia a la lista de pacientes
    private Paciente pEditar; // paciente que se esta editando
    private MainController main; // referencia al controlador principal

    public void preparar(ObservableList<Paciente> lista, Paciente p, MainController m) {
        this.listaRef = lista; // guarda la lista
        this.pEditar = p; // guarda el paciente
        this.main = m; // guarda el main

        if (p != null) { // si estamos editando pone los datos en los cuadros
            txtCurp.setText(p.getCurp());
            txtNombre.setText(p.getNombre());
            txtEdad.setText(String.valueOf(p.getEdad()));
            txtTelefono.setText(p.getTelefono());
            txtAlergias.setText(p.getAlergias());
        }
    }

    @FXML
    void onSave() {
        if (lblError != null) lblError.setText(""); // limpia el error anterior

        // saca los datos de los cuadros de texto
        String curp = txtCurp.getText().trim();
        String nombre = txtNombre.getText().trim();
        String tel = txtTelefono.getText().trim();
        String edadRaw = txtEdad.getText().trim();
        String alergias = txtAlergias.getText().trim();

        try {
            // validacion para campos obligatorios vacios
            if (curp.isEmpty() || nombre.isEmpty() || tel.isEmpty() || edadRaw.isEmpty()) {
                if (lblError != null) lblError.setText("Todos los campos son obligatorios");
                return; // detiene el guardado
            }

            int edad = Integer.parseInt(edadRaw); // intenta convertir la edad a numero

            if (pEditar == null) {
                // si es nuevo lo agrega a la lista principal
                listaRef.add(new Paciente(curp, nombre, edad, tel, alergias, "Activo"));
            } else {
                // si es edicion actualiza los datos del objeto que ya existia
                pEditar.setCurp(curp);
                pEditar.setNombre(nombre);
                pEditar.setEdad(edad);
                pEditar.setTelefono(tel);
                pEditar.setAlergias(alergias);
            }

            main.actualizarInterfaz(); // avisa a la ventana principal que actualice todo
            cerrar(); // cierra este formulario

        } catch (NumberFormatException e) {
            // si el usuario metio letras en la edad muestra este error
            if (lblError != null) lblError.setText("La edad debe ser un numero");
        }
    }

    @FXML void onCancel() { cerrar(); } // cierra sin guardar

    private void cerrar() {
        // busca la ventana y la cierra
        ((Stage) txtCurp.getScene().getWindow()).close();
    }
}