package com.example.consultorio.services;

import com.example.consultorio.models.Paciente;
import com.example.consultorio.repositories.PacienteRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PacienteService {
    private PacienteRepository repo = new PacienteRepository();

    public List<Paciente> loadData() throws IOException {
        List<String> lines = repo.readAllLines();
        List<Paciente> result = new ArrayList<>();
        for (String line : lines) {
            if (line == null || line.isBlank()) continue;
            String[] p = line.split(",", -1);
            result.add(new Paciente(p[0], p[1], Integer.parseInt(p[2]), p[3], p[4], p[5]));
        }
        return result;
    }

    public void addPaciente(String curp, String nom, int edad, String tel, String al) throws IOException {
        if (nom.length() < 5) throw new IllegalArgumentException("Nombre muy corto");
        if (edad < 0 || edad > 120) throw new IllegalArgumentException("Edad inválida");
        if (!tel.matches("\\d{10}")) throw new IllegalArgumentException("Teléfono debe ser de 10 dígitos");

        // Evitar duplicados por CURP
        for (Paciente p : loadData()) {
            if (p.getCurp().equalsIgnoreCase(curp)) throw new IllegalArgumentException("CURP ya registrado");
        }

        Paciente nuevo = new Paciente(curp, nom, edad, tel, al, "ACTIVO");
        repo.appendNewLine(nuevo.toCSV());
    }

    public void toggleEstatus(Paciente paciente) throws IOException {
        List<Paciente> lista = loadData();
        for (Paciente p : lista) {
            if (p.getCurp().equals(paciente.getCurp())) {
                p.setEstatus(p.getEstatus().equals("ACTIVO") ? "INACTIVO" : "ACTIVO");
            }
        }
        List<String> lines = lista.stream().map(Paciente::toCSV).collect(Collectors.toList());
        repo.writeAllLines(lines);
    }
}
