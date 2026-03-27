package com.example.consultorio.services;

import com.example.consultorio.models.Paciente;
import com.example.consultorio.repositories.PacienteRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PacienteService {
    private PacienteRepository repo = new PacienteRepository();

    public List<Paciente> getAll() throws IOException {
        List<String> lines = repo.readAllLines();
        List<Paciente> lista = new ArrayList<>();
        for (String line : lines) {
            if (line.isBlank()) continue;
            String[] p = line.split(",");
            lista.add(new Paciente(p[0], p[1], Integer.parseInt(p[2]), p[3], p[4], Boolean.parseBoolean(p[5])));
        }
        return lista;
    }

    public void save(Paciente p, boolean isEdit) throws IOException {
        List<Paciente> lista = getAll();

        if (!isEdit && lista.stream().anyMatch(pac -> pac.getCurp().equalsIgnoreCase(p.getCurp()))) {
            throw new IllegalArgumentException("La CURP ya existe.");
        }

        if (isEdit) {
            lista.removeIf(pac -> pac.getCurp().equalsIgnoreCase(p.getCurp()));
        }

        lista.add(p);
        updateFile(lista);
    }

    public void toggleStatus(String curp) throws IOException {
        List<Paciente> lista = getAll();
        for (Paciente p : lista) {
            if (p.getCurp().equalsIgnoreCase(curp)) {
                p.setActivo(!p.isActivo());
                break;
            }
        }
        updateFile(lista);
    }

    private void updateFile(List<Paciente> lista) throws IOException {
        List<String> lines = lista.stream().map(Paciente::toString).collect(Collectors.toList());
        repo.saveAllLines(lines);
    }
}
