package com.example.consultorio.repositories;

import com.example.consultorio.models.Paciente;
import java.io.*;
import java.util.*;

public class PacienteRepository {
    private final String archivo = "data/pacientes.csv";

    public List<Paciente> leer() {
        List<Paciente> lista = new ArrayList<>();
        File file = new File(archivo);
        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");
                if (d.length == 6) {
                    lista.add(new Paciente(d[0], d[1], Integer.parseInt(d[2]), d[3], d[4], d[5]));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
        return lista;
    }

    public void guardar(List<Paciente> lista) {
        // 1. Crear el objeto File para la carpeta
        File directorio = new File("data");

        // 2. Si la carpeta no existe, la creamos
        if (!directorio.exists()) {
            directorio.mkdir();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Paciente p : lista) {
                bw.write(p.getCurp() + "," + p.getNombre() + "," + p.getEdad() + "," +
                        p.getTelefono() + "," + p.getAlergias() + "," + p.getEstatus());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

