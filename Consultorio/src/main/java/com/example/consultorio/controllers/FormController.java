package com.example.consultorio.controllers;
import com.example.consultorio.services.PacienteService;
import com.example.consultorio.models.Paciente;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class FormController {
    @FXML
    private TextField txtCurp, txtNombre, txtEdad, txtTel, txtAlergias;
    @FXML
    private Label lblError;

    private PacienteService service;

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

            Stage stage = (Stage) txtCurp.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            lblError.setText("La edad debe ser un número");
        } catch (IllegalArgumentException e) {
            lblError.setText(e.getMessage());
        } catch (Exception e) {
            lblError.setText("Error inesperado al guardar");
        }
    }

    @FXML
    public void onCancel() {
        Stage stage = (Stage) txtCurp.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onSave() {
        lblError.setText("");

        String curp = txtCurp.getText().trim();
        String nombre = txtNombre.getText().trim();
        String tel = txtTelefono.getText().trim();
        String edadRaw = txtEdad.getText().trim();
        String alergias = txtAlergias.getText().trim();

        try {
            if (curp.isEmpty() || nombre.isEmpty() || tel.isEmpty() || edadRaw.isEmpty()) {
                lblError.setText(" Todos los campos son obligatorios");
                return;
            }

            if (nombre.length() < 10) {
                lblError.setText(" El nombre debe tener al menos 10 letras");
                return;
            }

            if (curp.length() != 18) {
                lblError.setText(" La CURP debe tener exactamente 18 caracteres");
                return;
            }

            try {
                Long.parseLong(tel); // Verificamos que sean solo números
                if (tel.length() != 10) {
                    lblError.setText("El telefono debe tener 10 digitos");
                    return;
                }
            } catch (NumberFormatException e) {
                lblError.setText("El telefono solo debe contener numeros");
                return;
            }

            int edad;
            try {
                edad = Integer.parseInt(edadRaw);
                if (edad < 0 || edad > 120) {
                    lblError.setText("La edad debe estar entre 0 y 120");
                    return;
                }
            } catch (NumberFormatException e) {
                lblError.setText("La edad debe ser un numero entero");
                return;
            }

            if (pEditar == null) {
                for (Paciente pac : listaRef) {
                    if (pac.getCurp().equalsIgnoreCase(curp)) {
                        lblError.setText("Error: Esta CURP ya existe");
                        return;
                    }
                }
            }

            if (pEditar == null) {
                // Nuevo paciente (se asume Activo por defecto)
                listaRef.add(new Paciente(curp, nombre, edad, tel, alergias, "Activo"));
            } else {

                pEditar.setNombre(nombre);
                pEditar.setEdad(edad);
                pEditar.setTelefono(tel);
                pEditar.setAlergias(alergias);
            }

            main.actualizarInterfaz();
            cerrar();

        } catch (Exception e) {
            lblError.setText("Error al guardar");
        }
    }

    @FXML
    void onCancel() {
        cerrar();
    }

    private void cerrar() {
        ((Stage) txtCurp.getScene().getWindow()).close();
    }
}
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

