package com.example.consultorio.controllers;

import com.example.consultorio.models.Paciente;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class FormController {
    @FXML
    private TextField txtCurp, txtNombre, txtEdad, txtTelefono, txtAlergias;
    @FXML
    private Label lblError;

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
            txtCurp.setDisable(true); // Se bloquea la CURP porque es el ID unico
        }
    }
}